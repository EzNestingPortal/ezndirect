import React, { Component } from "react";
import { connect } from "react-redux";
import { Redirect, withRouter } from "react-router";
import { RouteComponentProps, Link } from "react-router-dom";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import moment from "moment";

import {
  Container,
  Label,
  Button,
  Row,
  Col,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Input
} from "reactstrap";

import {
  AvForm,
  AvInput,
  AvGroup,
  AvField,
  AvRadioGroup,
  AvRadio
} from "availity-reactstrap-validation";

import "./enroll.scss";

import { handleRegister, back } from "app/modules/pricing2/pricing2.reducer";

const DateInput = ({ onChange, placeholder, name, value, onClick }) => (
  <AvField
    onChange={onChange}
    placeholder={placeholder}
    onClick={onClick}
    value={value}
    name={name}
    readOnly={true}
    validate={{
      required: {
        value: true,
        errorMessage: "Preferred Service Date is required."
      }
    }}
  />
);

export interface IPricingProps
  extends DispatchProps,
    RouteComponentProps<{ description: string }> {
  description: string;
  registrationSuccess: boolean;
  razorSuccess: boolean;
  saveInDbSuccess: boolean;
}

export interface IPricingState {
  registrationSuccess: boolean;
  razorSuccess: boolean;
  saveInDbSuccess: boolean;
  modal1: boolean;
  modal2: boolean;
  modal3: boolean;
  serviceStartDate: Date;
}

const now = new Date().toJSON().split("T")[0];

class Enroll extends Component<IPricingProps, IPricingState> {
  state: IPricingState = {
    registrationSuccess: false,
    razorSuccess: false,
    saveInDbSuccess: false,
    modal1: false,
    modal2: false,
    modal3: false,
    serviceStartDate: null
  };

  constructor(props) {
    super(props);

    this.toggle1 = this.toggle1.bind(this);
    this.toggle2 = this.toggle2.bind(this);
    this.toggle3 = this.toggle3.bind(this);
    this.handleDateChange = this.handleDateChange.bind(this);
    this.handleChangeRaw = this.handleChangeRaw.bind(this);
  }

  toggle1() {
    this.setState({
      modal1: !this.state.modal1
    });
  }

  toggle2() {
    this.setState({
      modal2: !this.state.modal2
    });
  }

  toggle3() {
    this.setState({
      modal3: !this.state.modal3
    });
  }

  handleDateChange(date) {
    this.setState({
      serviceStartDate: date
    });
  }

  handleChangeRaw(event) {
    const m = moment(event.target.value);

    if (m.isValid()) {
      this.handleDateChange(event.target.value);
    }
  }

  handleValidSubmit = (event, values) => {
    const headers = {
      "Content-Type": "application/json",
      ServerName: "AdEagles",
      Host: "AdEagles.0.razorsync.com",
      Connection: "Keep-Alive",
      Token: "f61ab5c0-77b4-4115-8e5a-0607c856baa6",
      "api-key": "EZN"
    };
    this.props.handleRegister(values, headers);
    event.preventDefault();
  };

  handleBack = event => {
    this.props.history.push("/");
    event.preventDefault();
  };

  inputChangedHandler = (event, inputIdentifier) => {
    localStorage.setItem(inputIdentifier, event.target.value);
  };
  render() {
    if (
      this.props.saveInDbSuccess === true &&
      this.props.razorSuccess === true
    ) {
      return <Redirect to="/register" />;
    }

    const form = (
      <Container className="formContainer">
        <Row className="justify-content-center">
          <Col md="8">
            <h1 id="register-title">Registration</h1>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            <AvForm onValidSubmit={this.handleValidSubmit}>
              <Row>
                <Col xs="12" sm="6">
                  <AvField
                    name="firstName"
                    placeholder="First Name"
                    onChange={event =>
                      this.inputChangedHandler(event, "firstName")
                    }
                    tabIndex="0"
                    autoFocus
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "First Name is required."
                      },
                      pattern: {
                        value: "^[_.@A-Za-z0-9- ]*$",
                        errorMessage:
                          "First Name can only contain letters and digits."
                      },
                      minLength: {
                        value: 1,
                        errorMessage:
                          "First Name is required to be at least 1 character."
                      },
                      maxLength: {
                        value: 50,
                        errorMessage:
                          "First Name cannot be longer than 50 characters."
                      }
                    }}
                  />
                </Col>
                <Col xs="12" sm="6">
                  <AvField
                    name="lastName"
                    placeholder="Last Name"
                    onChange={event =>
                      this.inputChangedHandler(event, "lastName")
                    }
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "Last Name is required."
                      },
                      pattern: {
                        value: "^[_.@A-Za-z0-9- ]*$",
                        errorMessage:
                          "Last Name can only contain letters and digits."
                      },
                      minLength: {
                        value: 1,
                        errorMessage:
                          "Last Name is required to be at least 1 character."
                      },
                      maxLength: {
                        value: 50,
                        errorMessage:
                          "Last Name cannot be longer than 50 characters."
                      }
                    }}
                  />
                </Col>
              </Row>

              <Row>
                <Col xs="12" sm="6">
                  <AvField
                    name="mobileNo"
                    placeholder="Mobile Number"
                    type="tel"
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "Mobile Number is invalid."
                      },
                      minLength: {
                        value: 1,
                        errorMessage:
                          "Mobile Number is required to be at least 10 character."
                      },
                      maxLength: {
                        value: 50,
                        errorMessage:
                          "Mobile Number cannot be longer than 10 characters."
                      }
                    }}
                  />
                </Col>
                <Col xs="12" sm="6">
                  <AvField
                    name="email"
                    type="email"
                    onChange={event => this.inputChangedHandler(event, "email")}
                    placeholder="Email"
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "Email is invalid."
                      },
                      minLength: {
                        value: 1,
                        errorMessage:
                          "Your email is required to be at least 5 characters."
                      },
                      maxLength: {
                        value: 50,
                        errorMessage:
                          "Your email cannot be longer than 50 characters."
                      }
                    }}
                  />
                </Col>
              </Row>

              <Row>
                <Col xs="12" sm="6">
                  <AvField
                    name="properties[0].address1"
                    placeholder="Service Address"
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "Address is required."
                      },
                      minLength: {
                        value: 1,
                        errorMessage:
                          "Address is required to be at least 5 characters."
                      },
                      maxLength: {
                        value: 100,
                        errorMessage:
                          "Address cannot be longer than 100 characters."
                      }
                    }}
                  />
                </Col>
                <Col xs="12" sm="6">
                  <AvField
                    name="properties[0].city"
                    placeholder="City"
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "City is required."
                      },
                      pattern: {
                        value: "^[_.@A-Za-z0-9- ]*$",
                        errorMessage:
                          "City can only contain letters and digits."
                      },
                      minLength: {
                        value: 1,
                        errorMessage:
                          "City is required to be at least 5 characters."
                      },
                      maxLength: {
                        value: 50,
                        errorMessage:
                          "City cannot be longer than 50 characters."
                      }
                    }}
                  />
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="6">
                  <AvField
                    name="properties[0].state"
                    placeholder="State"
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "State is required."
                      },
                      pattern: {
                        value: "^[_.@A-Za-z0-9- ]*$",
                        errorMessage:
                          "State can only contain letters and digits."
                      },
                      minLength: {
                        value: 1,
                        errorMessage:
                          "State is required to be at least 5 character."
                      },
                      maxLength: {
                        value: 50,
                        errorMessage:
                          "State cannot be longer than 50 characters."
                      }
                    }}
                  />
                </Col>

                <Col xs="12" sm="6">
                  <AvField
                    name="properties[0].zip"
                    placeholder="Zip"
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "Zip is required."
                      },
                      pattern: {
                        value: "^[_.@0-9-]*$",
                        errorMessage: "Zip can only contain digits."
                      },
                      minLength: {
                        value: 1,
                        errorMessage:
                          "Zip is required to be at least 5 character."
                      },
                      maxLength: {
                        value: 5,
                        errorMessage: "Zip cannot be longer than 5 characters."
                      }
                    }}
                  />
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="6">
                  <AvField
                    type="select"
                    name="properties[0].param1"
                    validate={{
                      required: {
                        value: true,
                        errorMessage: "Current Grass length is required"
                      }
                    }}
                  >
                    <option value="">
                      Choose Current Grass Length at your property
                    </option>
                    <option value="3">3 inches or less</option>
                    <option value="4">4 inches or less</option>
                    <option value="5">5 inches or less</option>
                    <option value="6">6 inches or less</option>
                    <option value="9">9 inches or less</option>
                    <option value="12">12 inches or less</option>
                    <option value="15">15 inches or less</option>
                    <option value="18">18 inches or less</option>
                    <option value="24">24 inches or more</option>
                  </AvField>
                </Col>

                <Col xs="12" sm="6">
                  <DatePicker
                    name="properties[0].serviceStartDate"
                    id="serviceStartDate"
                    selected={this.state.serviceStartDate}
                    onChange={this.handleDateChange}
                    dateFormat="MM/dd/yyyy"
                    minDate={new Date()}
                    placeholderText="Preferred Service Date"
                    onChangeRaw={event => this.handleChangeRaw(event)}
                    required={true}
                    customInput={
                      <DateInput
                        onChange={this.handleDateChange}
                        placeholder="Preferred Service Date"
                        name="properties[0].serviceStartDate"
                        value={this.state.serviceStartDate}
                        onClick={onclick}
                      />
                    }
                    className="form-control"
                  />
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="6">
                  <AvInput
                    type="hidden"
                    name="properties[0].description"
                    id="description"
                    value={
                      this.props.location.state === undefined
                        ? ""
                        : this.props.location.state.q
                    }
                  />
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="6">
                  <AvInput
                    type="hidden"
                    name="properties[0].lawnSize"
                    id="lawnSize"
                    value={localStorage.getItem("lawnSize")}
                  />
                  <AvInput
                    type="hidden"
                    name="properties[0].propertySize"
                    id="propertySize"
                    value={localStorage.getItem("propertySize")}
                  />
                  <AvInput
                    type="hidden"
                    name="properties[0].floors"
                    id="floors"
                    value={localStorage.getItem("floors")}
                  />
                  <AvInput
                    type="hidden"
                    name="properties[0].planId"
                    id="planId"
                    value={localStorage.getItem("subscriptionId")}
                  />
                  <AvInput
                    type="hidden"
                    name="properties[0].planType"
                    id="planType"
                    value={localStorage.getItem("subscriptionName")}
                  />
                  <AvInput
                    type="hidden"
                    name="properties[0].actualPrice"
                    id="planType"
                    value={localStorage.getItem("actualPrice")}
                  />
                  <AvInput
                    type="hidden"
                    name="properties[0].discountPrice"
                    id="planType"
                    value={localStorage.getItem("discountedPrice")}
                  />
                  <AvInput
                    type="hidden"
                    name="properties[0].country"
                    id="country"
                    value="US"
                  />
                  {/* <AvInput
                    type="hidden"
                    name="properties[0].serviceStartDate"
                    id="serviceStartDate"
                    value={this.state.serviceStartDate==null?'':this.state.serviceStartDate.toJSON().split("T")[0]}
                  /> */}
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="6">
                  <AvField
                    name="properties[0].notes"
                    id="notes"
                    type="textarea"
                    placeholder="Any additional information you'd like to provide"
                    maxLength="500"
                  />
                </Col>
                <Col xs="12" sm="6">
                  <AvField
                    name="properties[0].referralCode"
                    id="referralCode"
                    type="text"
                    placeholder="Referral Code"
                    maxLength="50"
                  />
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="12">
                  <AvGroup check>
                    <Label>
                      <AvInput
                        type="checkbox"
                        name="properties[0].flowerBed"
                        id="flowerBed"
                      />{" "}
                      Request Quotes for Flower and Tree Beds Maintenance and
                      Trimming bushes along with your regular mowing services
                    </Label>
                  </AvGroup>
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="12">
                  <AvGroup check>
                    <Label>
                      <AvInput
                        type="checkbox"
                        name="properties[0].quarterlyPestControl"
                        id="quarterlyPestControl"
                      />{" "}
                      Add quarterly pest control service for just $90/service
                    </Label>
                  </AvGroup>
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="12">
                  <AvGroup check>
                    <Label>
                      <AvInput
                        type="checkbox"
                        required
                        name="properties[0].agree"
                        id="agree"
                      />{" "}
                      I agree to the{" "}
                      <Link to="#" onClick={this.toggle1} className="link">
                        Terms of Service
                      </Link>,{" "}
                      <Link to="#" onClick={this.toggle3} className="link">
                        First Service Policy, Corner Lot, Long Grass Policy
                      </Link>{" "}
                      and{" "}
                      <Link to="#" onClick={this.toggle2} className="link">
                        Service Property Details
                      </Link>{" "}
                      provided accurately
                    </Label>
                  </AvGroup>
                </Col>
              </Row>
              <Row>
                <Col xs="12" sm="12">
                  <div className="buttonpanel">
                    <Button
                      color="info"
                      type="button"
                      onClick={this.handleBack}
                    >
                      Change Property Details
                    </Button>
                    <Button color="primary" type="submit">
                      Continue
                    </Button>
                  </div>
                </Col>
              </Row>
            </AvForm>
          </Col>
        </Row>
      </Container>
    );

    const tcText = (
      <>
        <p>Terms and Conditions:</p>
        <p>Last Updated August 2018 </p>

        <p>
          PLEASE READ THESE TERMS OF USE (“AGREEMENT” OR “TERMS OF USE”)
          CAREFULLY BEFORE USING THE WEBSITE, MOBILE APPLICATIONS, AND SERVICES
          OFFERED BY EZ Nesting LLC. AND ITS SUBSIDIARIES (COLLECTIVELY,
          “COMPANY”). THIS AGREEMENT SETS FORTH THE LEGALLY BINDING TERMS AND
          CONDITIONS FOR YOUR USE OF THE WEBSITE AT www.eznesting.com,
          www.pricing.eznesting.com, MOBILE APPLICATIONS, AND SERVICES PROVIDED
          BY COMPANY (COLLECTIVELY, THE “SERVICES”).
        </p>

        <p>
          Whether you are a visitor, home service professional, or a home
          service recipient (visitor, lawn care provider, pest service provider,
          window cleaning service provider or providers for any kind of home
          services and service recipient, collectively, “users” or “you”), by
          using the Services in any manner, including but not limited to
          visiting or browsing the Services, you agree to be bound by this
          Agreement, including those additional terms and conditions and
          policies referenced herein and/or available by hyperlink. This
          Agreement applies to all users of the Services. If you are a home
          service professional (lawn care, pest, maid service, pool service,
          window cleaning, trash cleaning, etc.), you will be required to agree
          to supplementary terms governing your use of the Company platform and
          provision of home services.
        </p>

        <p>
          If you have any questions, please refer to the help section of the
          Services or contact us at support@eznesting.com.
        </p>

        <p>1. Company is a Platform</p>

        <p>
          Company acts as a platform to allow home service professionals who
          comply with Company’s policies to connect with other users who may
          desire home services. Company is not directly involved in any
          transactions between users. As a result, Company has no control over
          the quality of the home services provided by the service providers or
          the truth or accuracy of the information provided by users, or the
          ability of service professionals to complete home services in a timely
          manner, if at all.
        </p>

        <p>
          Company cannot guarantee the true identity, age, and nationality of an
          user. Company encourages users to communicate directly through the
          tools available on the Services. You may also wish to consider
          independent user verification or using a third-party service that
          provides additional user verification.
        </p>

        <p>
          You agree that Company is a platform and as such is not responsible or
          liable for any home services or other content posted by you, other
          users, or third parties on the Services. Your use of the Services is
          at your own risk.
        </p>

        <p>2. Membership Eligibility</p>

        <p>
          Services are available only to, and may only be used by, individuals
          who are 18 years and older who can form legally binding contracts
          under applicable law. You represent and warrant that you are at least
          18 years old and that all registration information you submit is
          accurate and truthful. Company may, in its sole discretion, refuse to
          offer access to or use of the Services to any person or entity and
          change its eligibility criteria at any time. This provision is void
          where prohibited by law and the right to access the Services is
          revoked in those jurisdictions.
        </p>

        <p>
          Individuals under the age of 18 must at all times use the Services
          only in conjunction with and under the supervision of a parent or
          legal guardian who is at least 18 years of age. In all cases, the
          adult is the user and is responsible for any and all activities.
        </p>

        <p>
          You agree to comply with all local laws regarding online conduct and
          acceptable content. In addition, you must abide by Company’s policies
          as stated in the Agreement and the Company’s Privacy Policy, as well
          as all other operating rules, policies and procedures that may be
          published from time to time on the Services by Company, each of which
          is incorporated herein by reference and each of which may be updated
          by Company from time to time without notice to you.
        </p>

        <p>
          In addition, some services offered by Company may be subject to
          additional terms and conditions issued by Company or a third party;
          your use of those services is subject to the additional terms and
          conditions, which are incorporated into this Agreement by this
          reference.
        </p>

        <p>
          Keep your password secure. You are fully responsible for all activity,
          liability and damage resulting from your failure to keep your password
          confidential. You agree to immediately notify Company of any
          unauthorized use of your password or any breach of security. You also
          agree that Company cannot and will not be liable for any loss or
          damage arising from your failure to keep your password secure. You
          agree not to provide your username and password information in
          combination to any other party other than Company without Company’s
          express written permission.
        </p>

        <p>
          You must keep your account information up-to-date and accurate at all
          times, including a valid email address.
        </p>

        <p>
          Services are not available to temporarily or permanently banned users.
          Company reserves the right, in Company’s sole discretion, to cancel
          unconfirmed or inactive accounts. Company reserves the right to refuse
          service to anyone, for any reason, at any time.
        </p>

        <p>3. Prohibited, Questionable and Infringing Activities</p>

        <p>
          You are solely responsible for your conduct and activities on and
          regarding to the Services and any and all information that you submit,
          post, upload, and display on the Services.
        </p>

        <p>Your use of Company will not:</p>

        <p>• be false, inaccurate or misleading;</p>
        <p>
          • infringe upon any third-party’s intellectual property rights or
          rights of publicity or privacy;
        </p>
        <p>
          • violate this Agreement, any website policy or community guidelines,
          or any applicable law, statute, ordinance or regulation;
        </p>
        <p>
          • be defamatory, libelous, unlawfully threatening, unlawfully
          harassing, impersonate or intimidate any person (including Company
          staff or other users), or falsely state or otherwise misrepresent your
          affiliation with any person, through for example, the use of similar
          email address, nicknames, or creation of false account(s) or any other
          method or device;
        </p>
        <p>• take any action that may undermine online reviews or feedback;</p>
        <p>• be obscene or contain child pornography; and</p>
        <p>
          • contain or transmit any code of a destructive nature that may
          damage, detrimentally interfere with, surreptitiously intercept or
          expropriate any system, data or personal information.
        </p>
        <p>4. License Grant & Restrictions</p>
        <p>
          The Company hereby grants you a non-exclusive, non-transferable, right
          to use the Services, solely for your own personal, non-commercial
          purposes, subject to the terms and conditions of this Agreement. All
          rights not expressly granted to you are reserved by the Company and
          its licensors. You will not (i) license, sublicense, sell, resell,
          transfer, assign, distribute or otherwise commercially exploit or make
          available to any third party the Services in any way; (ii) modify or
          make derivative works based upon the Services; (iii) create Internet
          "links" to the Services; (iv) reverse engineer the Services; (v)
          access the Services in order to (a) build a competitive product or
          service, (b) build a product using similar ideas, features, functions
          or graphics of the Services, or (c) copy any ideas, features,
          functions or graphics of the Services, or (vi) launch an automated
          program or script, including, but not limited to, web spiders, web
          crawlers, web robots, web ants, web indexers, bots, viruses or worms,
          or any program which may make multiple server requests per second, or
          unduly burdens or hinders the operation or performance of the
          Services.
        </p>
        <p>5. Payment Terms</p>
        <p>
          Any fees which the Company may charge you for the Services are due
          immediately upon scheduling and/or completion of your home services
          and are non-refundable. This no refund policy will apply at all times
          regardless of your decision to terminate your usage, the Company's
          decision to terminate your usage, disruption caused to our Services
          either planned, accidental or intentional, or any reason whatsoever.
          The Company reserves the right to determine final prevailing pricing -
          Please note the pricing information published on the website may not
          reflect the accurate pricing as prices vary based on service property
          type, property size, property location, property age, grass length at
          property, or any other service related parameters. The Company may
          charge additional fees for any type of home services provided to
          compensate for the Company and/or service providers time, equipment
          and efforts. The Company, at its sole discretion, make promotional
          offers with different features and different rates to any of our
          customers. These promotional offers, unless made to you, will have no
          bearing whatsoever on your offer or contract. The Company may change
          the fees for our Services as we deem necessary for our business. We
          encourage you to check back at our website periodically if you are
          interested about how we charge for the Services.
        </p>
        <p>6. Intellectual Property Ownership</p>
        <p>
          The Company alone (and its licensors, where applicable) will own all
          right, title and interest, including all related intellectual property
          rights, in and to the Services. This Agreement is not a sale and does
          not convey to you any rights of ownership in or related to the
          Services, or any intellectual property rights owned by the Company.
          The Company name, the Company logo, and the product names associated
          with the Services are trademarks of the Company or third parties, and
          no right or license is granted to use them.
        </p>
        <p>7. Personal Information</p>
        <p>
          As part of using the Services, you may obtain personal information,
          including email addresses, from other users. Without obtaining prior
          permission from the user, this personal information will only be used
          for Services-related communications. Company has not granted you a
          license to use the information for unsolicited commercial messages or
          unauthorized transactions. Without limiting the foregoing, without
          express consent from the user, you are not licensed to add any Company
          user to your email or physical mail list. For more information, see
          Company’s Privacy Policy.
        </p>
        <p>
          By using the Services, you agree that the Company, Services, Service
          Providers may send you informational (and the other home services
          required or may be required at the service property based on season,
          age, location, etc.) text (SMS) messages and emails as part of the
          normal business operation of your use of the Services.
        </p>
        <p>8. Feedback</p>
        <p>
          Company considers any unsolicited suggestions, ideas, proposals or
          other material submitted to it by users via the Services (“Feedback”)
          to be non-confidential and non-proprietary, and Company will not be
          liable for the disclosure or use of any Feedback. If, at Company’s
          request, any user sends Feedback to improve the Services, Company will
          also consider that Feedback to be non-confidential and non-proprietary
          and Company will not be liable for use or disclosure of the Feedback.
          Any communication by you to Company is subject to this Agreement. You
          hereby grant and agree to grant Company, under all of your rights in
          the Feedback, a worldwide, non-exclusive, perpetual, irrevocable,
          royalty-free, fully-paid, sublicensable and transferable right and
          license to incorporate, use, publish and exploit Feedback for any
          purpose, commercial or otherwise, without compensation or accounting
          to you and without further recourse by you.
        </p>
        <p>9. Information Control</p>
        <p>
          Company does not control the information provided by users. You may
          find some information provided by users to be offensive, harmful,
          inaccurate, or deceptive. There are also risks of dealing with
          underage persons or people acting under false pretense. Additionally,
          there may also be risks dealing with foreign nationals. By using the
          Services, you agree to accept these risks and that Company (and
          Company’s officers, directors, agents, subsidiaries, joint ventures
          and employees) is not responsible for any and all acts or omissions of
          users of the Services.
        </p>
        <p>
          Company is not responsible for the availability of outside websites or
          resources linked to or referenced on the Services. Company does not
          endorse and is not responsible or liable for any content, advertising,
          products, or other materials on or available from these websites or
          resources. You agree that Company will not be responsible or liable,
          directly or indirectly, for any damage or loss caused or alleged to be
          caused by or in connection with the use of or reliance on any content,
          goods or services available on or through any other websites or
          resources.
        </p>
        <p>10. Access and Interference</p>
        <p>
          Company may contain robot exclusion headers which contain internal
          rules for software usage. Much of the information on Company is
          updated on a real-time basis and is proprietary or is licensed to
          Company by users or third-parties. You agree that you will not use any
          robot, spider, scraper or other automated means to access the Services
          for any purpose whatsoever, except to the extent expressly permitted
          in writing by Company. Additionally, you agree that you will not:
        </p>
        <p>
          • take any action that imposes, or may impose, in Company’s sole
          discretion, an unreasonable or disproportionately large load on
          Company’s infrastructure;
        </p>
        <p>
          • copy, reproduce, modify, create derivative works from, distribute or
          publicly display any user information (except your own);
        </p>
        <p>
          • interfere or attempt to interfere with the proper working of the
          Services or any activities conducted on the Services; or
        </p>
        <p>
          • bypass Company’s robot exclusion headers or other measures Company
          may use to prevent or restrict access to the Services.
        </p>
        <p>11. Breach</p>
        <p>
          Without limiting any other remedies, Company may, without notice,
          remove any user posting, warn Company’s community of a user’s actions,
          issue a warning to a user, temporarily suspend or terminate a user’s
          account, prohibit access to the Services, and take technical and legal
          steps to keep a user from accessing or using the Services and refuse
          to provide the Services to a user if Company (1) suspects a user has
          breached this Agreement or the Privacy Policy, (2) is unable to verify
          or authenticate any of your personal information, or (3) reasonably
          believes that a user’s actions may cause legal liability or financial
          loss to users or to Company.
        </p>
        <p>12. Privacy</p>
        <p>
          Except as provided in the Privacy Policy, Company will not sell or
          disclose your personal information to third parties without your
          express consent. Company stores and processes information on servers
          hosted by Amazon Web Services that are protected by physical as well
          as technological security.
        </p>
        <p>13. No Warranty</p>
        <p>
          COMPANY, COMPANY’S SUBSIDIARIES, OFFICERS, DIRECTORS, EMPLOYEES, AND
          COMPANY’S SUPPLIERS PROVIDE COMPANY’S THE SERVICES “AS IS,” “AS
          AVAILABLE,” AND WITHOUT ANY WARRANTY OR CONDITION, EXPRESS, IMPLIED OR
          STATUTORY. COMPANY, COMPANY’S SUBSIDIARIES, OFFICERS, DIRECTORS,
          EMPLOYEES AND COMPANY’S SUPPLIERS SPECIFICALLY DISCLAIM ANY IMPLIED
          WARRANTIES OF TITLE, MERCHANTABILITY, PERFORMANCE, FITNESS FOR A
          PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN ADDITION, NO ADVICE OR
          INFORMATION (ORAL OR WRITTEN) OBTAINED BY YOU FROM COMPANY WILL CREATE
          ANY WARRANTY. SOME STATES DO NOT ALLOW THE DISCLAIMER OF IMPLIED
          WARRANTIES, SO THE FOREGOING DISCLAIMER MAY NOT APPLY TO YOU. THIS
          WARRANTY GIVES YOU SPECIFIC LEGAL RIGHTS AND YOU MAY ALSO HAVE OTHER
          LEGAL RIGHTS THAT VARY FROM STATE TO STATE.
        </p>
        <p>14. Limitation of Liability</p>
        <p>
          IN NO EVENT WILL COMPANY, OR COMPANY’S SUBSIDIARIES, OFFICERS,
          DIRECTORS, EMPLOYEES OR COMPANY’S SUPPLIERS BE LIABLE FOR ANY DAMAGES
          WHATSOEVER, WHETHER DIRECT, INDIRECT, GENERAL, SPECIAL, COMPENSATORY,
          CONSEQUENTIAL, OR INCIDENTAL, ARISING OUT OF OR RELATING TO THE
          CONDUCT OF YOU OR ANYONE ELSE IN CONNECTION WITH THE USE OF THE
          SERVICES, OR THIS AGREEMENT, INCLUDING WITHOUT LIMITATION, LOST
          PROFITS, BODILY INJURY, EMOTIONAL DISTRESS, OR ANY SPECIAL, INCIDENTAL
          OR CONSEQUENTIAL DAMAGES.
        </p>
        <p>
          COMPANY’S LIABILITY, AND THE LIABILITY OF COMPANY’S SUBSIDIARIES,
          OFFICERS, DIRECTORS, EMPLOYEES, AND SUPPLIERS, TO YOU OR ANY THIRD
          PARTIES IN ANY CIRCUMSTANCE IS LIMITED TO THE GREATER OF (A) THE
          AMOUNT OF FEES YOU PAY TO COMPANY IN THE 12 MONTHS PRIOR TO THE ACTION
          GIVING RISE TO LIABILITY, AND (B) $100. SOME STATES DO NOT ALLOW THE
          EXCLUSION OR LIMITATION OF INCIDENTAL OR CONSEQUENTIAL DAMAGES, SO THE
          ABOVE LIMITATION OR EXCLUSION MAY NOT APPLY TO YOU.
        </p>
        <p>15. Indemnity</p>
        <p>
          You agree to indemnify and hold Company and Company’s parent,
          subsidiaries, affiliates, officers, directors, agents, and employees,
          harmless from any claim or demand, including attorneys’ fees, made by
          any third party due to or arising out of your breach of this Agreement
          or your violation of any law or the rights of a third party.
        </p>
        <p>16. No Guarantee</p>
        <p>
          Company does not guarantee continuous, uninterrupted access to the
          Services, and operation of the Services may be interfered with by
          numerous factors outside Company’s control.
        </p>

        <p>17. Dispute Resolution and Release</p>

        <p>
          Company is not responsible for your interactions with other users. Any
          dispute between you and another user must be resolved between
          yourselves. Company reserves the right, but have no obligation, to
          monitor disputes between users.
        </p>

        <p>For any dispute between you and the Company:</p>

        <p>
          You agree that any dispute, claim or controversy arising out of or
          relating to this Agreement or the breach, termination, enforcement,
          interpretation or validity thereof or the use of the Services
          (collectively, “Disputes”) will be settled by binding arbitration
          between you and the Company, except that each party retains the right
          to bring an individual action in small claims court and the right to
          seek injunctive or other equitable relief in a court of competent
          jurisdiction to prevent the actual or threatened infringement or
          misappropriation of a party’s intellectual property rights. You
          acknowledge and agree that you and the Company are each waiving the
          right to a trial by jury or to participate as a plaintiff or class in
          any purported class action or representative proceeding. Further,
          unless both you and the Company otherwise agree in writing, the
          arbitrator may not consolidate more than one person’s claims, and may
          not otherwise preside over any form of any class or representative
          proceeding. If this specific paragraph is held unenforceable, then the
          entirety of this “Dispute Resolution” section will be deemed void.
          Except as provided in the preceding sentence, this “Dispute
          Resolution” section will survive any termination of this Agreement.
        </p>

        <p>
          The arbitration will be administered by the American Arbitration
          Association (“AAA”) in accordance with the Commercial Arbitration
          Rules and the Supplementary Procedures for Consumer Related Disputes
          (the “AAA Rules”) then in effect, except as modified by this “Dispute
          Resolution” section. The Federal Arbitration Act will govern the
          interpretation and enforcement of this Section.
        </p>

        <p>
          A party who desires to initiate arbitration must provide the other
          party with a written Demand for Arbitration as specified in the AAA
          Rules. The arbitrator will be either a retired judge or an attorney
          licensed to practice law in the state of Texas and will be selected by
          the parties from the AAA’s roster of consumer dispute arbitrators. If
          the parties are unable to agree upon an arbitrator within seven days
          of delivery of the Demand for Arbitration, then the AAA will appoint
          the arbitrator in accordance with the AAA Rules.
        </p>

        <p>
          Unless you and the Company otherwise agree, the arbitration will be
          conducted in the county where you reside. If your claim does not
          exceed $10,000, then the arbitration will be conducted solely on the
          basis of documents you and the Company submit to the arbitrator,
          unless you request a hearing or the arbitrator determines that a
          hearing is necessary. If your claim exceeds $10,000, your right to a
          hearing will be determined by the AAA Rules. Subject to the AAA Rules,
          the arbitrator will have the discretion to direct a reasonable
          exchange of information by the parties, consistent with the expedited
          nature of the arbitration.
        </p>

        <p>
          The arbitrator will render an award within the time frame specified in
          the AAA Rules. The arbitrator’s decision will include the essential
          findings and conclusions upon which the arbitrator based the award.
          Judgment on the arbitration award may be entered in any court having
          jurisdiction thereof. The arbitrator’s award damages must be
          consistent with the terms of the “Limitation of Liability” section
          below as to the types and the amounts of damages for which a party may
          be held liable. The arbitrator may award declaratory or injunctive
          relief only in favor of the claimant and only to the extent necessary
          to provide relief warranted by the claimant’s individual claim.
        </p>

        <p>
          Your responsibility to pay any AAA filing, administrative and
          arbitrator fees will be solely as set forth in the AAA Rules.
        </p>

        <p>
          Notwithstanding the provisions of the modification-related provisions
          above, if the Company changes this “Dispute Resolution” section after
          the date you first accepted this Agreement (or accepted any subsequent
          changes to this Agreement), you may reject these changes by providing
          us written notice of your rejection by mail or hand delivery to: EZ
          Nesting LLC, 9910 Tucker Lane, Frisco, TX, 75035, or by email from the
          email address associated with your Account to: support@eznesting.com,
          within 30 days of the date the change became effective, as indicated
          in the “Last update” date above. In order to be effective, the notice
          must include your full name and clearly indicate your intent to reject
          changes to this “Dispute Resolution” section. By rejecting changes,
          you are agreeing that you will arbitrate any Dispute between you and
          the Company in accordance with the provisions of this “Dispute
          Resolution” section as of the date you first accepted this Agreement
          (or accepted any subsequent changes to this Agreement).You release
          Company (and Company’s officers, directors, agents, subsidiaries,
          joint ventures and employees) from any and all claims, demands and
          damages (actual and consequential) of every kind and nature, known and
          unknown, suspected and unsuspected, disclosed and undisclosed, arising
          out of or in any way connected with disputes with one or more users,
          or an outside party.
        </p>

        <p>18. Miscellaneous</p>

        <p>
          If any provision of this Agreement is held unenforceable, then the
          provision will be modified to reflect the parties’ intention. All
          remaining provisions of this Agreement will remain in full force and
          effect. You and Company are independent contractors, and no agency,
          partnership, joint venture, employee-employer or franchiser-franchisee
          relationship is intended or created by this Agreement. Company
          reserves the right to modify or terminate the Services for any reason,
          without notice, at any time. This Agreement will be governed by and
          construed in accordance with the laws of the State of Texas, including
          its conflicts of law rules. You agree that any dispute arising from or
          relating to the subject matter of this Agreement will be governed by
          the exclusive jurisdiction and venue of the state and Federal courts
          of Travis County, Texas. Unless otherwise specified herein, all
          notices will be in writing and will be deemed to have been duly given
          when received, if personally delivered or sent by certified or
          registered mail, return receipt requested; when receipt is
          electronically confirmed, if transmitted by facsimile or e-mail; or
          the day after it is sent, if sent for next day delivery by recognized
          overnight delivery service. Electronic notices should be sent to
          support@eznesting.com. All provisions of this Agreement which by their
          nature should survive termination will survive termination, including,
          without limitation, ownership provisions, warranty disclaimers,
          indemnity and limitations of liability.
        </p>

        <p>
          Company reserves the right to alter this Agreement and any other
          policies at any time, so please review the policies frequently. If
          Company makes a material change Company will notify you here, by
          email, by means of a notice on our home page, or other places Company
          deems appropriate. What constitutes a “material change” will be
          determined at Company’s sole discretion, in good faith, and using
          common sense and reasonable judgment.
        </p>
      </>
    );

    const spText = (
      <>
        <p>
          I confirm that I’ve provided service property details accurately
          online. In case, if there is a discrepancy in the service property
          details provided, then I authorize EZ Nesting to charge my account
          with additional fees and/or price difference; based on my service plan
          and the true service property details, for each service provided.
        </p>
      </>
    );

    const lgText = (
      <>
        <p>Long Grass Policy:</p>
        <p>
          In cases where your grass is overgrown (greater than 6 inches), we
          often have to charge an additional fee. This compensates the lawn
          service crew for the additional time it takes to complete the job.
          Therefore, we reserve the right to charge up to 2x the original price
          in cases of overgrown grass, although the charge is often much less.
        </p>
        <p>
          In cases where the grass is extremely overgrown (taller than 12
          inches), we will get you a custom quote for the completion of the job,
          as in these cases it often takes much more time and/or specialty
          equipment.
        </p>
        <p>
          These policies ensure that our hardworking service crews are fairly
          compensated for the time they spend on each job.
        </p>

        <p>Cornet Lot Policy:</p>
        <p>
          I understand that if my service property is in the corner, then I
          authorize EZ Nesting to charge an additional fee as applicable as it
          can take more time to service the property.
        </p>

        <p>First Service Policy:</p>
        <p>
          I understand that if my service property has not been maintained on
          regular basis, then it may require additional efforts, work and/or
          time to complete the services and EZ Nesting may have to charge an
          additional fee. Hence, I authorize EZ Nesting to charge an additional
          fee (as applicable) to compensate for any type of services being
          performed for the first time (or any services performed when service
          property is not maintained on regular basis).
        </p>
      </>
    );

    const tcModal = (
      <Modal isOpen={this.state.modal1} toggle={this.toggle1}>
        <ModalHeader toggle={this.toggle1}>Terms & Conditions</ModalHeader>
        <ModalBody>{tcText}</ModalBody>
      </Modal>
    );

    const spModal = (
      <Modal isOpen={this.state.modal2} toggle={this.toggle2}>
        <ModalHeader toggle={this.toggle2}>
          Service Property Details Provided ACCURATELY
        </ModalHeader>
        <ModalBody>{spText}</ModalBody>
      </Modal>
    );

    const lgModal = (
      <Modal isOpen={this.state.modal3} toggle={this.toggle3}>
        <ModalHeader toggle={this.toggle3}>
          First Service, Corner Lot and Long Grass Policy
        </ModalHeader>
        <ModalBody>{lgText}</ModalBody>
      </Modal>
    );
    return (
      <div>
        {form} {tcModal} {spModal} {lgModal}
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    showPlan: state.pricing.showResults,
    subscriptions: state.pricing.subscriptions,
    registrationSuccess: state.pricing.registrationSuccess,
    razorSuccess: state.pricing.razorSuccess,
    saveInDbSuccess: state.pricing.saveInDbSuccess
  };
};

type StateProps = ReturnType<typeof mapStateToProps>;

const mapDispatchToProps = { handleRegister, back };
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(withRouter(Enroll));
