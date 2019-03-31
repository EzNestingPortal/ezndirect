import React, { Component } from "react";
import { connect } from "react-redux";
import { withRouter, Redirect, RouteComponentProps } from "react-router";
import { Link } from "react-router-dom";

import {
  Container,
  TabContent,
  TabPane,
  Nav,
  NavItem,
  NavLink,
  Label,
  Button,
  Form,
  FormGroup,
  Input,
  Row,
  Col,
  Modal,
  ModalHeader,
  ModalBody
} from "reactstrap";
import classnames from "classnames";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import "./pricing2.scss";

import {
  handleGetMyPrice,
  reset,
  login,
  PricingState
} from "app/modules/pricing2/pricing2.reducer";

import { updateObject } from "app/shared/util/Utility";

export interface IPricingProps
  extends StateProps,
    DispatchProps,
    RouteComponentProps<{ description: string }> {}

export interface IPricingState {
  pricingForm;
  activeTab;
  isSuccess;
  modal: boolean;
}

class Pricing extends Component<IPricingProps, IPricingState> {
  public refs: {
    theDiv: HTMLElement;
  };

  state: IPricingState = {
    pricingForm: {
      lawnSize: {
        value: "3000"
      },
      propertySize: {
        value: "2500"
      },
      floors: {
        value: "1"
      }
    },
    activeTab: "1",
    isSuccess: false,
    modal: false
  };

  constructor(props) {
    super(props);
    this.props.handleGetMyPrice(3000, 2500, 1);
    this.showMoreDetails = this.showMoreDetails.bind(this);
  }

  focusDiv() {
    this.refs.theDiv.focus();
  }

  showMoreDetails() {
    this.setState({
      modal: !this.state.modal
    });
  }

  toggle(tab) {
    if (this.state.activeTab !== tab) {
      this.setState({
        activeTab: tab
      });
    }
  }

  inputChangedHandler = (event, inputIdentifier) => {
    const updatedFormElement = updateObject(
      this.state.pricingForm[inputIdentifier],
      {
        value: event.target.value
      }
    );

    const updatedPricingForm = updateObject(this.state.pricingForm, {
      [inputIdentifier]: updatedFormElement
    });

    let pricingForm = Object.assign({}, this.state.pricingForm); //creating copy of object
    pricingForm[inputIdentifier].value = event.target.value; //updating value

    this.setState({ pricingForm });

    this.submithandler(event);
  };

  submithandler = event => {
    let lawnSize = this.state.pricingForm.lawnSize.value;
    let propertySize = this.state.pricingForm.propertySize.value;
    let floors = this.state.pricingForm.floors.value;

    this.props.handleGetMyPrice(lawnSize, propertySize, floors);
    event.preventDefault();
  };

  focusAndGetPrice = event => {
    if (this.props.showPlan) {
      this.focusDiv();
    }
    this.submithandler(event);
  };

  enrollhandler = event => {
    const lawnSize = this.state.pricingForm.lawnSize.value;
    const propertySize = this.state.pricingForm.propertySize.value;
    const floors = this.state.pricingForm.floors.value;
    const planId = this.state.activeTab;
    const subscriptionId = this.props.subscriptions[this.state.activeTab - 1]
      .subscriptionId;
    const subscriptionName = this.props.subscriptions[this.state.activeTab - 1]
      .subscriptionName;
    const discountedPrice = this.props.subscriptions[this.state.activeTab - 1]
      .discountedPrice;
    const actualPrice = this.props.subscriptions[this.state.activeTab - 1]
      .originalAmount;
    const description =
      lawnSize +
      "|" +
      propertySize +
      "|" +
      floors +
      "|" +
      planId +
      "|" +
      subscriptionId +
      "|" +
      subscriptionName +
      "|" +
      discountedPrice;

    localStorage.setItem("lawnSize", lawnSize);
    localStorage.setItem("propertySize", propertySize);
    localStorage.setItem("floors", floors);
    localStorage.setItem("planId", planId);
    localStorage.setItem("subscriptionId", subscriptionId);
    localStorage.setItem("subscriptionName", subscriptionName);
    localStorage.setItem("discountedPrice", discountedPrice);
    localStorage.setItem("actualPrice", actualPrice);

    this.props.history.push({
      pathname: "/enroll",
      search: "?q",
      state: { q: description }
    });

    event.preventDefault();
  };

  render() {
    const { showPlan, subscriptions } = this.props;

    let form = (
      <>
        <Row className="justify-content-center">
          <Col sm="8" md="6" lg="4" xl="3">
            <div className="title">
              Join the Network of 400+ Happy Home Owners
            </div>

            <div className="header">No Contract Plans, Cancel Anytime!</div>
            <div className="builder">
              <Label for="lawnSize">Estimated Lawn Size</Label>
              <Input
                type="select"
                name="lawnSize"
                id="lawnSize"
                onChange={event => this.inputChangedHandler(event, "lawnSize")}
              >
                <option value="1500">Less than 1,500 Sq. Ft.</option>
                <option value="3000" selected>
                  1,501 Sq. Ft. to 3,000 Sq. Ft.
                </option>
                <option value="4000">3,001 Sq. Ft. to 4,000 Sq. Ft.</option>
                <option value="5000">4,001 Sq. Ft. to 5,000 Sq. Ft.</option>
                <option value="6500">5,001 Sq. Ft. to 6,500 Sq. Ft.</option>
                <option value="8500">6,501 Sq. Ft. to 8,500 Sq. Ft.</option>
                <option value="10000">8,501 Sq. Ft. to 10,000 Sq. Ft.</option>
                <option value="11500">10,001 Sq. Ft. to 11,500 Sq. Ft.</option>
                <option value="14000">11,501 Sq. Ft. to 14,000 Sq. Ft.</option>
                <option value="17500">14,001 Sq. Ft. to 17,500 Sq. Ft.</option>
                <option value="19500">17,501 Sq. Ft. to 19,500 Sq. Ft.</option>
              </Input>

              <Label for="propertySize">Estimated Property Size</Label>
              <Input
                type="select"
                name="propertySize"
                id="propertySize"
                onChange={event =>
                  this.inputChangedHandler(event, "propertySize")
                }
              >
                <option value="1500">Less than 1,500 Sq. Ft.</option>
                <option value="2000">1,501 Sq. Ft. to 2,000 Sq. Ft.</option>
                <option value="2500" selected>
                  2,001 Sq. Ft. to 2,500 Sq. Ft.
                </option>
                <option value="3000">2,501 Sq. Ft. to 3,000 Sq. Ft.</option>
                <option value="3500">3,001 Sq. Ft. to 3,500 Sq. Ft.</option>
                <option value="4000">3,501 Sq. Ft. to 4,000 Sq. Ft.</option>
                <option value="4500">4,001 Sq. Ft. to 4,500 Sq. Ft.</option>
                <option value="5000">4,501 Sq. Ft. to 5,000 Sq. Ft.</option>
                <option value="5500">5,001 Sq. Ft. to 5,500 Sq. Ft.</option>
                <option value="6000">5,501 Sq. Ft. to 6,000 Sq. Ft.</option>
                <option value="6500">6,001 Sq. Ft. to 6,500 Sq. Ft.</option>
                <option value="7500">6,501 Sq. Ft. to 7,500 Sq. Ft.</option>
                <option value="8500">7,501 Sq. Ft. to 8,500 Sq. Ft.</option>
                <option value="10000">8,501 Sq. Ft. to 10,000 Sq. Ft.</option>
                <option value="12000">10,001 Sq. Ft. to 12,000 Sq. Ft.</option>
                <option value="15000">12,001 Sq. Ft. to 15,000 Sq. Ft.</option>
                <option value="17500">15,001 Sq. Ft. to 17,500 Sq. Ft.</option>
                <option value="19500">17,501 Sq. Ft. to 19,500 Sq. Ft.</option>
              </Input>

              <Label for="floors">Number of Floors</Label>
              <Input
                type="select"
                name="floors"
                id="floors"
                onChange={event => this.inputChangedHandler(event, "floors")}
              >
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
              </Input>
              <div className="buttonpanel1">
                <Button
                  color="warning ezn-warning-button"
                  onClick={this.focusAndGetPrice}
                >
                  GET MY PRICE
                </Button>
              </div>
            </div>

            <div ref="theDiv" tabIndex={0} className="plan">
              {this.props.showPlan ? (
                <Nav pills fill>
                  {this.props.subscriptions.map((subscription, i) => (
                    <NavItem key={`NavId${i + 1}`}>
                      <NavLink
                        className={classnames({
                          active: this.state.activeTab === `${i + 1}`
                        })}
                        onClick={() => {
                          this.toggle(`${i + 1}`);
                        }}
                      >
                        ${subscription.discountedPrice}
                      </NavLink>
                    </NavItem>
                  ))}
                </Nav>
              ) : null}

              {this.props.showPlan ? (
                <TabContent activeTab={this.state.activeTab}>
                  {this.props.subscriptions.map((subscription, j) => (
                    <TabPane tabId={`${j + 1}`} key={`ID${j + 1}`}>
                      <Row>
                        <Col sm="12">
                          <p id="title">{subscription.subscriptionName}</p>
                        </Col>
                      </Row>
                      <Row>
                        <Col sm="12">
                          {/* <p id="highlight">Yearly SAVINGS ${subscription.yearlySavings}</p> */}
                          <p id="highlight">
                            Pay-Bi-weekly (No Charges in{" "}
                            {subscription.idleMonths})
                          </p>
                        </Col>
                      </Row>
                      {subscription.services.map((service, k) => (
                        <Row key={"RowId${k + 1}"}>
                          {service.available && service.service.free ? (
                            <Col sm="12">
                              {service.available ? (
                                <FontAwesomeIcon
                                  icon="check"
                                  color={
                                    !service.service.free
                                      ? "#6DB65B"
                                      : "#F6BD1F"
                                  }
                                  size="sm"
                                />
                              ) : !service.service.free ? (
                                <FontAwesomeIcon
                                  icon="times"
                                  color="gray"
                                  size="sm"
                                />
                              ) : null}
                              {service.available && service.service.free ? (
                                <span id="service" className="available">
                                  {" "}
                                  {service.service.serviceName}
                                </span>
                              ) : null}
                            </Col>
                          ) : null}
                        </Row>
                      ))}

                      {subscription.services.map((service, k) => (
                        <Row key={`RowId${k + 1}`}>
                          {!service.service.free ? (
                            <Col sm="12">
                              {service.available ? (
                                <FontAwesomeIcon
                                  icon="check"
                                  color={
                                    !service.service.free
                                      ? "#6DB65B"
                                      : "#F6BD1F"
                                  }
                                  size="sm"
                                />
                              ) : !service.service.free ? (
                                <FontAwesomeIcon
                                  icon="times"
                                  color="gray"
                                  size="sm"
                                />
                              ) : null}
                              {service.available && service.service.free ? (
                                <span id="service" className="available">
                                  {" "}
                                  {service.service.serviceName}
                                </span>
                              ) : null}
                              {service.service.visible &&
                              !service.service.free ? (
                                <span
                                  id="service"
                                  className={
                                    service.available
                                      ? "available"
                                      : "notAvailable"
                                  }
                                >
                                  {" "}
                                  {service.service.serviceName}
                                </span>
                              ) : null}
                            </Col>
                          ) : null}
                        </Row>
                      ))}
                      <Row>
                        <Col sm="12">
                          <FontAwesomeIcon icon="plus" color="red" size="sm" />{" "}
                          $90/service for Quarterly Pest Control
                        </Col>
                      </Row>
                    </TabPane>
                  ))}

                  <div className="buttonpanel1">
                    <Link
                      to="#"
                      className="moreDetails"
                      onClick={this.showMoreDetails}
                    >
                      More Details...
                    </Link>
                  </div>

                  <div className="buttonpanel1">
                    <Button
                      color="primary ezn-primary-button"
                      onClick={this.enrollhandler}
                    >
                      Sign Up for FREE
                    </Button>
                  </div>
                </TabContent>
              ) : null}
            </div>
          </Col>
        </Row>
      </>
    );

    let mdText = (
      <>
        <p>
          <b>What comes with my service plan?</b>
        </p>
        <p>
          All Services (as listed below) will be provided based on the season
          and EZ Nesting’s optimized and/or neighborhood schedule. You will be
          notified for every single service, scheduled, requested or completed.
          Our guys will also send you a notification when they are on their way.
          You will also have complete access to your online Account and all
          payments are online.
        </p>

        <p>
          <b>BASIC CARE</b>
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Grass height
          will be left to a seasonally appropriate height after each mowing.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> If grass
          height is more than 6 inches, there will be extra charges as it
          requires twice the time and different equipment’s.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Grass
          Clippings will be left in the Yard as it acts as Fertilizer.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Edging along
          Sidewalks, Pathways and Driveway.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Blow Grass
          Clippings off Driveway and Pathway.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> We come back
          and fix the issues, if an issue is reported within 48 hours of the
          service completion time and date.
        </p>

        <p>
          <b>PRIME CARE</b>
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Includes all
          “BASIC CARE” plan plus.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Weed eater
          will be used to do edging in the backyard.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Pre-emergent
          will be applied in (or before) spring every year.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Post-emergent
          will be applied in Fall every year.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Lawn
          Fertilizer will be applied based on the season and your property
          location.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Weed Control
          will be applied based on the season and your property location.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Second Weed
          Treatment service is COMPLETELY FREE for Lawn size smaller than 4000
          sq.ft
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Second Lawn
          Fertilizer service is COMPLETELY FREE for Lawn size smaller than 4000
          sq.ft
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> This service
          is provided once-a-year. As part of Mulching service, we will refill
          tree and flower beds with 20 bags of mulching.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Quarterly
          pest control will be provided by our 3rd party licensed pest control
          technician/company. These technicians are thoroughly Vetted and
          Screened.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> PLUS 5%
          Discount on any other service that you request from us.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> If your lawn
          condition is bad, then you will have to request for additional
          services for your lawn treatment to have our licensed pro treat your
          lawn
        </p>

        <p>
          <b>GRAND CARE</b>
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Includes all
          “PRIME CARE” plan plus.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> This service
          is provided once-a-year. As part of Exterior Window Cleaning service,
          we will cover up to 30 windows (smaller than 4 by 6 ft) per your plan.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Two trash
          cans are covered as part of the yearly trash can cleaning service.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> This service
          is provided once-a-year. As part of Carpet Cleaning service, we will
          cover up to 3 areas of 200 square feet each.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> This service
          is provided once-a-year. As part of Concrete Patio Power washing
          service, we will power wash patio up to 10 ft by 15ft.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> We provide
          FREE Roof Inspection service once-a-year to ensure there are no leaks
          or damages in your roof.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> We also
          provide a FREE Body Massage session, for 45 mins, once-a-year to one
          of your family members. You can choose massage parlors in our network
          to claim this message.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Additional
          10% Discount on any other service that you request from us.
        </p>

        <p>
          <b>ELITE CARE</b>
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Includes all
          “GRAND CARE” plan plus.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> This service
          is provided once-a-year. As part of Inside Window Cleaning service, we
          will cover up to 30 windows (smaller than 4 by 6 ft) per your plan.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> This service
          is provided once-a-year. As part of Concrete Driveway Power washing
          service, we will power wash patio up to 20 ft by 20ft.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> This service
          is provided once a year. As part of Gutter Cleaning service, we will
          clean the gutters from one or two storey houses.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> We provide
          FREE yearly Photoshoot for 30 mins, with 3 high resolution photos.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> We also
          provide FREE Technician visits once-a-year to check the appliances.
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> We also
          provide FREE Estimates for Home Repairs
        </p>
        <p>
          <FontAwesomeIcon icon="check" color="green" size="sm" /> Additional
          15% Discount on any other service that you request from us.
        </p>

        <p>
          ** If windows size (or there are more windows), patio size, room size
          for carpet cleaning or flower and tree bed size is greater than the
          sizes listed above, then there may be additional charges.
        </p>
      </>
    );
    const mdModal = (
      <Modal isOpen={this.state.modal} toggle={this.showMoreDetails}>
        <ModalHeader toggle={this.showMoreDetails}>More Details</ModalHeader>
        <ModalBody>{mdText}</ModalBody>
      </Modal>
    );

    return (
      <div>
        {form} {mdModal}{" "}
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    showPlan: state.pricing.showResults,
    subscriptions: state.pricing.subscriptions,
    description: state.pricing.description
  };
};

type StateProps = ReturnType<typeof mapStateToProps>;

const mapDispatchToProps = { handleGetMyPrice, reset, login };
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withRouter(Pricing));
