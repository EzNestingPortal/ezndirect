import React, { Component } from "react";
import { connect } from "react-redux";

import Slider from "react-slick";

import "./pricing.scss";
import BuildControl from "app/modules/pricing/BuildControl/BuildControl";
import Button from "app/modules/pricing/UI/Button/Button";

import Plan from "app/modules/pricing/plan/plan";

import { handleGetMyPrice, reset } from "app/modules/pricing/pricing.reducer";

import { checkValidity, updateObject } from "app/shared/util/Utility";

export interface IPricingProps extends StateProps, DispatchProps {}

export interface IPricingState {
  pricingForm;
  showResults;
  isFormValid;
}

class Pricing extends Component<IPricingProps, IPricingState> {
  state: IPricingState = {
    pricingForm: {
      lotSize: {
        label: "Lot Size",
        elementType: "select",
        elementConfig: {
          type: "text",
          placeholder: "Lot Size",
          options: [
            { value: "0", displayValue: "Lot Size..." },
            { value: "5000", displayValue: "Up to 5000 Sq. Ft." },
            { value: "10000", displayValue: "5000 Sq. Ft. to 10,000 Sq. Ft." },
            {
              value: "15000",
              displayValue: "10,000 Sq. Ft. to 15,000 Sq. Ft."
            },
            { value: "20000", displayValue: "Above 15,000 Sq. Ft." }
          ]
        },
        value: "",
        validation: {
          required: true
        },
        valid: false,
        touched: false
      },
      floorSize: {
        label: "Property Size",
        elementType: "select",
        elementConfig: {
          type: "text",
          placeholder: "Property Size",
          options: [
            { value: "0", displayValue: "Property Size..." },
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
      floors: {
        label: "Storeys",
        elementType: "select",
        elementConfig: {
          type: "text",
          placeholder: "Storeys",
          options: [
            { value: "0", displayValue: "Storeys..." },
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
      }
    },
    isFormValid: false,
    showResults: false
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
    let lotSize = this.state.pricingForm["lotSize"].value;
    let floorSize = this.state.pricingForm["floorSize"].value;
    let floors = this.state.pricingForm["floors"].value;

    this.props.handleGetMyPrice(lotSize, floorSize, floors);
    event.preventDefault();
  };

  render() {
    const { showPlan, subscriptions } = this.props;

    const settings = {
      dots: true,
      arrows: false,
      autoplay: true,
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1
    };

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

    let form = (
      <form>
        <div className="FormControl">
          <BuildControl
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
            key={formElement2.id}
            elementtype={formElement2.config.elementType}
            elementconfig={formElement2.config.elementConfig}
            value={formElement2.value}
            invalid={!formElement2.config.valid}
            shouldValidate={formElement2.config.validation}
            touched={formElement2.config.touched}
            changed={event => this.inputChangedHandler(event, formElement2.id)}
          />

          <BuildControl
            key={formElement3.id}
            elementtype={formElement3.config.elementType}
            elementconfig={formElement3.config.elementConfig}
            value={formElement3.value}
            invalid={!formElement3.config.valid}
            shouldValidate={formElement3.config.validation}
            touched={formElement3.config.touched}
            changed={event => this.inputChangedHandler(event, formElement3.id)}
          />
        </div>
        <div className="ButtonPanel">
          <Button btnType="btn-success" clicked={this.submithandler}>
            GET MY PRICE
          </Button>
        </div>
      </form>
    );

    return (
      <div>
        <div className="FormContainer">{form}</div>
        {this.props.showPlan ? (
          <Slider {...settings}>
            {this.props.subscriptions.map((subscription, i) => (
              <div key={`ID${i + 1}`}>
                <Plan
                  circle={`Circle${i + 1}`}
                  heading={`Heading${i + 1}`}
                  header={`Header${i + 1}`}
                  planName={subscription.subscriptionName}
                  discountedPrice={subscription.discountedPrice}
                  originalAmount={subscription.originalAmount}
                  yearlySavings={subscription.yearlySavings}
                  idleMonths={subscription.idleMonths}
                  services={subscription.services}
                />
              </div>
            ))}
          </Slider>
        ) : null}
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    showPlan: state.pricing.showResults,
    subscriptions: state.pricing.subscriptions
  };
};
type StateProps = ReturnType<typeof mapStateToProps>;

const mapDispatchToProps = { handleGetMyPrice, reset };
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Pricing);
