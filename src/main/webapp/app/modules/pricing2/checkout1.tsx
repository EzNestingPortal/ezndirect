import React, { Component } from "react";
import { Container, Row, Col, Button, Label } from "reactstrap";
import { toast } from "react-toastify";

import {
  CardElement,
  CardNumberElement,
  CardExpiryElement,
  CardCVCElement,
  PostalCodeElement,
  injectStripe
} from "react-stripe-elements";
import {
  AvForm,
  AvInput,
  AvGroup,
  AvField,
  AvRadioGroup,
  AvRadio
} from "availity-reactstrap-validation";
import { updateObject } from "app/shared/util/Utility";
import "./checkout.scss";

export interface ICheckoutState {
  showBillingSection: boolean;
  checkoutForm: any;
}

class Checkout extends Component<ICheckoutState> {
  state: ICheckoutState = {
    showBillingSection: false,
    checkoutForm: {
      address1: {
        value: ""
      },
      city: {
        value: ""
      },
      state: {
        value: ""
      }
    }
  };

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.inputChangedHandler = this.inputChangedHandler.bind(this);
  }

  inputChangedHandler = (event, inputIdentifier) => {
    let checkoutForm = Object.assign({}, this.state.checkoutForm); //creating copy of object
    checkoutForm[inputIdentifier].value = event.target.value; //updating value

    this.setState({ checkoutForm });
  };

  async handleSubmit(ev, values) {
    let isValid = true;
    let forms = document.getElementsByClassName("needs-validation");

    let validation = Array.prototype.filter.call(forms, function(form) {
      if (form.checkValidity() === false) {
        isValid = false;
        /* ev.preventDefault();
                ev.stopPropagation(); */
      }
    });
    let billingAddress = this.state.checkoutForm.address1.value;
    let billingCity = this.state.checkoutForm.city.value;
    let billingState = this.state.checkoutForm.state.value;

    if (isValid) {
      let name =
        localStorage.getItem("firstName") +
        " " +
        localStorage.getItem("lastName");
      let emailId = localStorage.getItem("emailId");

      // @ts-ignore
      let { token } = await this.props.stripe.createToken({ name: name });

      let formData = new FormData();
      formData.set("name", name);
      formData.set("email", emailId);
      formData.set("stripeToken", token.id);
      formData.set("addressLine1", billingAddress);
      formData.set("city", billingCity);
      formData.set("state", billingState);

      let response = await fetch("/api/create", {
        method: "POST",
        body: formData
      });

      if (response.ok) {
        console.log("Purchase complete!");
        toast.success("Payment Complete!!!");
      } else {
        console.log("Purchase failed!");
        toast.error("Payment failed. Please try after some time");
      }
      ev.preventDefault();
    }
  }

  handleClick() {
    this.setState({ showBillingSection: !this.state.showBillingSection });
  }

  render() {
    const BillingSection = (
      <>
        <Row>
          <Col>
            <AvField
              name="address1"
              placeholder="Billing Address"
              onChange={event => this.inputChangedHandler(event, "address1")}
              validate={{
                required: {
                  value: true,
                  errorMessage: "Billing Address is required."
                },
                minLength: {
                  value: 1,
                  errorMessage:
                    "Billing Address is required to be at least 5 characters."
                },
                maxLength: {
                  value: 100,
                  errorMessage:
                    "Billing Address cannot be longer than 100 characters."
                }
              }}
            />
          </Col>
        </Row>
        <Row>
          <Col>
            <AvField
              name="city"
              placeholder="City"
              onChange={event => this.inputChangedHandler(event, "city")}
              validate={{
                required: {
                  value: true,
                  errorMessage: "City is required."
                },
                pattern: {
                  value: "^[_.@A-Za-z0-9- ]*$",
                  errorMessage: "City can only contain letters and digits."
                },
                minLength: {
                  value: 1,
                  errorMessage: "City is required to be at least 5 characters."
                },
                maxLength: {
                  value: 50,
                  errorMessage: "City cannot be longer than 50 characters."
                }
              }}
            />
          </Col>
        </Row>
        <Row>
          <Col>
            <AvField
              name="state"
              placeholder="State"
              onChange={event => this.inputChangedHandler(event, "state")}
              validate={{
                required: {
                  value: true,
                  errorMessage: "State is required."
                },
                pattern: {
                  value: "^[_.@A-Za-z0-9- ]*$",
                  errorMessage: "State can only contain letters and digits."
                },
                minLength: {
                  value: 1,
                  errorMessage: "State is required to be at least 5 character."
                },
                maxLength: {
                  value: 50,
                  errorMessage: "State cannot be longer than 50 characters."
                }
              }}
            />
          </Col>
        </Row>
      </>
    );
    return (
      <Container>
        <Row className="justify-content-center">
          <Col>
            <p className="thanks-title">Next Steps</p>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col xl="6">
            <p className="thanks-footer">
              You have selected the {localStorage.getItem("subscriptionName")}{" "}
              Plan @ ${localStorage.getItem("discountedPrice")}
            </p>
            <p className="thanks-header">
              Now that you have registered with us, please add your credit card
              information below. Our website is completely secure to add your
              payment information as we use the payment systems used by UBER,
              LYFT, AIRBNB, etc
            </p>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col xl="6">
            <div className="formContainer">
              <Row>
                <Col>
                  <CardElement className="form-control" />
                  <br />
                  <div id="card-errors" role="alert" />
                </Col>
              </Row>
              <AvForm className="needs-validation">
                <Row>
                  <Col>
                    <AvGroup check>
                      <Label>
                        <AvInput
                          type="checkbox"
                          name="select"
                          id="select"
                          onClick={this.handleClick}
                        />{" "}
                        Select this, if your billing address is NOT same as your
                        Service address
                      </Label>
                    </AvGroup>
                  </Col>
                </Row>
                {this.state.showBillingSection ? BillingSection : null}

                <Row className="justify-content-center">
                  <Col
                    xs="12"
                    sm="12"
                    md="6"
                    lg="6"
                    xl="6"
                    className="checkoutComp"
                  >
                    <Button
                      color="primary ezn-primary-button"
                      onClick={this.handleSubmit}
                      type="submit"
                    >
                      Add Card Information
                    </Button>
                  </Col>
                </Row>
              </AvForm>
            </div>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col xl="6">
            <p className="thanks-footer">
              Please note that all our payments are online and the First Payment
              will be deducted ONLY when the first service is scheduled for your
              property. Thereafter, recurring charges (plus taxes) will be
              applied/deducted on bi-weekly basis as per the home care plan you
              have selected.
            </p>
          </Col>
        </Row>
      </Container>
    );
  }
}

export default injectStripe(Checkout);
