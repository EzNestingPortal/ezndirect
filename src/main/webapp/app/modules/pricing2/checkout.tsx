import React, { Component } from "react";
import {
  Alert,
  Container,
  Row,
  Col,
  Button,
  Label,
  Modal,
  ModalHeader,
  ModalBody
} from "reactstrap";

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
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { toast } from "react-toastify";

import "./checkout.scss";

export interface ICheckoutState {
  showBillingSection: boolean;
  checkoutForm: any;
  modal: boolean;
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
    },
    modal: false
  };

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleClick = this.handleClick.bind(this);
    this.inputChangedHandler = this.inputChangedHandler.bind(this);
    this.showPaymentSuccess = this.showPaymentSuccess.bind(this);
  }
  showPaymentSuccess() {
    this.setState({
      modal: !this.state.modal
    });
  }

  inputChangedHandler = (event, inputIdentifier) => {
    const checkoutForm = Object.assign({}, this.state.checkoutForm); //creating copy of object
    checkoutForm[inputIdentifier].value = event.target.value; //updating value

    this.setState({ checkoutForm });
  };

  async handleSubmit(ev, values) {
    let isValid = true;
    const forms = document.getElementsByClassName("needs-validation");

    let validation = Array.prototype.filter.call(forms, function(form) {
      if (form.checkValidity() === false) {
        isValid = false;
        /* ev.preventDefault();
                ev.stopPropagation(); */
      }
    });
    const billingAddress = this.state.checkoutForm.address1.value;
    const billingCity = this.state.checkoutForm.city.value;
    const billingState = this.state.checkoutForm.state.value;

    if (isValid) {
      const name =
        localStorage.getItem("firstName") +
        " " +
        localStorage.getItem("lastName");
      const emailId = localStorage.getItem("emailId");

      // @ts-ignore
      const { token } = await this.props.stripe.createToken({ name: name });

      const formData = new FormData();
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
        this.showPaymentSuccess();
      } else {
        toast.error("Payment failed. Please try after some time");
      }
      ev.preventDefault();
    }
  }

  handleClick() {
    this.setState({ showBillingSection: !this.state.showBillingSection });
  }

  render() {
    let psText = (
      <>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> You have just
          taken a big step for stress free home maintenance.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> You should
          have received a couple of emails with next steps.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> After first
          service, recurring charges (plus taxes) will be applied on bi-weekly
          basis as per the home care plan selected.
        </p>
      </>
    );

    const psModal = (
      <Modal
        isOpen={this.state.modal}
        toggle={this.showPaymentSuccess}
        className="success"
      >
        <ModalHeader toggle={this.showPaymentSuccess}>
          Congratulations!
        </ModalHeader>
        <ModalBody>{psText}</ModalBody>
      </Modal>
    );

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
            <p className="thanks-title">NEXT STEPS</p>
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
            <div className="left-align">
              <p>
                <FontAwesomeIcon icon="check" color="green" size="sm" /> You
                have selected the {localStorage.getItem("subscriptionName")}{" "}
                Plan @ ${localStorage.getItem("discountedPrice")}
              </p>
              <p>
                <FontAwesomeIcon icon="check" color="green" size="sm" /> Add
                your Payment information using our completely secure website.
              </p>
              <p>
                <FontAwesomeIcon icon="check" color="green" size="sm" /> We use
                secure payment systems used by UBER, LYFT, AIRBNB, etc.
              </p>
              <p>
                <FontAwesomeIcon icon="check" color="green" size="sm" /> First
                payment will be deducted only after first service is scheduled
                and confimred at your property.
              </p>
            </div>
          </Col>
        </Row>
        {this.state.modal ? psModal : null}
      </Container>
    );
  }
}

export default injectStripe(Checkout);
