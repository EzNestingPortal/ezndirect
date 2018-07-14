import React, { Component } from "react";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import Collapsible from "react-collapsible";

import "./BasicCarePlan.scss";
import BuildControl from "../BuildControl/BuildControl";
import Button from "../UI/Button/Button";

import * as actions from "../actions/index";
import { updateObject, checkValidity } from "../../../shared/util/Utility";

class BasicCarePlan extends Component {
  state = {
    pricingForm: {
      lotSize: {
        label: "Lot Size",
        elementType: "select",
        elementConfig: {
          type: "text",
          placeholder: "Lot Size",
          options: [
            { value: "0", displayValue: "Please Select..." },
            { value: "8000", displayValue: "8000 Sq. Ft." },
            { value: "7000", displayValue: "7000 Sq. Ft." },
            { value: "6000", displayValue: "6000 Sq. Ft." },
            { value: "5000", displayValue: "5000 Sq. Ft." }
          ]
        },
        value: "",
        validation: {
          required: true
        },
        valid: false,
        touched: false
      },
      propSize: {
        label: "Property Size",
        elementType: "select",
        elementConfig: {
          type: "text",
          placeholder: "Property Size",
          options: [
            { value: "0", displayValue: "Please Select..." },
            { value: "4000", displayValue: "4000 Sq. Ft." },
            { value: "3000", displayValue: "3000 Sq. Ft." },
            { value: "2000", displayValue: "2000 Sq. Ft." },
            { value: "1000", displayValue: "1000 Sq. Ft." }
          ]
        },
        value: "",
        validation: {
          required: true
        },
        valid: false,
        touched: false
      },
      storeys: {
        label: "Storeys",
        elementType: "select",
        elementConfig: {
          type: "text",
          placeholder: "Storeys",
          options: [
            { value: "0", displayValue: "Please Select..." },
            { value: "1", displayValue: "1" },
            { value: "2", displayValue: "2" },
            { value: "3", displayValue: "3" },
            { value: "4", displayValue: "4" }
          ]
        },
        value: "",
        validation: {
          required: true
        },
        valid: false,
        touched: false
      },
      frequency: {
        label: "Frequency",
        elementType: "select",
        elementConfig: {
          type: "text",
          placeholder: "Frequency",
          options: [
            { value: "0", displayValue: "Please Select..." },
            { value: "1", displayValue: "Weekly" },
            { value: "2", displayValue: "Bi-Weekly" },
            { value: "3", displayValue: "Monthly" }
          ]
        },
        value: "",
        validation: {
          required: true
        },
        valid: false,
        touched: false
      }
    },
    isFormValid: false
  };

  inputChangedHandler = (event, inputIdentifier) => {
    const updatedFormElement = updateObject(
      this.state.pricingForm[inputIdentifier],
      {
        value: event.target.value,
        valid: checkValidity(
          event.target.value,
          this.state.pricingForm[inputIdentifier].validation
        ),
        touched: true
      }
    );
    const updatedPricingForm = updateObject(this.state.pricingForm, {
      [inputIdentifier]: updatedFormElement
    });

    let isFormValid = true;
    for (let inputIdentifier in updatedPricingForm) {
      isFormValid = updatedPricingForm[inputIdentifier].valid && isFormValid;
    }
    this.setState({
      pricingForm: updatedPricingForm,
      isFormValid: isFormValid
    });
  };

  submithandler = event => {
    event.preventDefault();

    const formData = {};

    for (let formElementIdentifier in this.state.pricingForm) {
      formData[formElementIdentifier] = this.state.pricingForm[
        formElementIdentifier
      ].value;
    }

    const order = {
      planType: 1,
      orderData: formData
    };

    this.props.onGetMyPrice(order);
    //this.props.handleUpdatePrice(this.props.price);
  };
  reset = () => {
    this.setState(prevState => {
      return {
        price: 33
      };
    });
  };

  proceedToOrder = () => {
    this.props.history.replace("/auth");
  };

  render() {
    const formElementsArray = [];
    for (let key in this.state.pricingForm) {
      formElementsArray.push({
        id: key,
        config: this.state.pricingForm[key]
      });
    }

    let formElement1 = formElementsArray[0];
    let formElement2 = formElementsArray[1];
    let formElement3 = formElementsArray[2];
    let formElement4 = formElementsArray[3];

    let form = (
      //<form onSubmit={this.submithandler}>

      <form>
        <div className="BuildControl">
          <BuildControl
            label={formElement1.config.label}
            key={formElement1.id}
            elementtype={formElement1.config.elementType}
            elementconfig={formElement1.config.elementConfig}
            value={formElement1.value}
            invalid={!formElement1.config.valid}
            shouldValidate={formElement1.config.validation}
            touched={formElement1.config.touched}
            changed={event => this.inputChangedHandler(event, formElement1.id)}
          />

          <BuildControl
            label={formElement2.config.label}
            key={formElement2.id}
            elementtype={formElement2.config.elementType}
            elementconfig={formElement2.config.elementConfig}
            value={formElement2.value}
            invalid={!formElement2.config.valid}
            shouldValidate={formElement2.config.validation}
            touched={formElement2.config.touched}
            changed={event => this.inputChangedHandler(event, formElement2.id)}
          />
        </div>
        <div className="BuildControl">
          <BuildControl
            label={formElement3.config.label}
            key={formElement3.id}
            elementtype={formElement3.config.elementType}
            elementconfig={formElement3.config.elementConfig}
            value={formElement3.value}
            invalid={!formElement3.config.valid}
            shouldValidate={formElement3.config.validation}
            touched={formElement3.config.touched}
            changed={event => this.inputChangedHandler(event, formElement3.id)}
          />

          <BuildControl
            label={formElement4.config.label}
            key={formElement4.id}
            elementtype={formElement4.config.elementType}
            elementconfig={formElement4.config.elementConfig}
            value={formElement4.value}
            invalid={!formElement4.config.valid}
            shouldValidate={formElement4.config.validation}
            touched={formElement4.config.touched}
            changed={event => this.inputChangedHandler(event, formElement4.id)}
          />
        </div>
        <div className="ButtonPanel">
          <Button btnType="btn-danger" clicked={this.reset}>
            RESET
          </Button>
          <Button btnType="btn-success" clicked={this.submithandler}>
            GET MY PRICE
          </Button>
          <Button btnType="btn-primary" clicked={this.proceedToOrder}>
            ORDER
          </Button>
        </div>
      </form>
    );

    let details = (
      <div className="PlanContainer">
        <div className="Plan">
          <div className="BasicCircle">
            <h1>${this.props.price1}</h1>
            <h3>Pay Bi-weekly</h3>
          </div>
        </div>
        <div className="BasicPlanType">
          <h1>BASIC PLAN</h1>
          <h4>Originally $60</h4>
        </div>
        <div className="PlanDetails">
          <Collapsible
            trigger="Benefits you get"
            triggerClassName="BasicCollapsibleHeader"
            triggerOpenedClassName="BasicCollapsibleHeader"
            open={window.innerWidth > 600 ? true : false}
          >
            <p>Yearly SAVINGS $345</p>
            <p>No Charges in Dec, Jan, Feb</p>
            <p>Twice-monthly Lawn Mowing and Blowing</p>
            <p>Weed-eating (twice-monthly)</p>
            <p>Edging and Grass Trimming (twice-monthly)</p>
            <p>Lawn Fertilizer (thrice a year)</p>
            <p>Weed Control (thrice a year)</p>
            <p>--</p>
            <p>--</p>
            <p>--</p>
          </Collapsible>
        </div>
      </div>
    );
    return (
      <div className="PlanContainer">
        {details}

        <div className="BasicPricingBuilder">{form}</div>
      </div>
    );
  }
}

const mapStoreToProps = state => {
  return {
    price1: state.pricing.price1
  };
};

const mapDispatchToProps = dispatch => {
  return {
    onGetMyPrice: order => dispatch(actions.getMyPrice(order))
  };
};

export default withRouter(
  connect(
    mapStoreToProps,
    mapDispatchToProps
  )(BasicCarePlan)
);
