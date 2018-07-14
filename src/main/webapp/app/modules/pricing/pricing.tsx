import React, { Component } from "react";
import { connect } from "react-redux";
import Slider from "react-slick";
import * as actions from "./actions/index";

import "./pricing.scss";
import BasicCarePlan from "./plan/BasicCarePlan";
import LawnCarePlan from "./plan/LawnCarePlan";
import PrimeCarePlan from "./plan/PrimeCarePlan";
import GrandCarePlan from "./plan/GrandCarePlan";

class PricingContainer extends Component {
  render() {
    const settings = {
      dots: true,
      arrows: false,
      autoplay: true,
      infinite: true,
      speed: 500,
      slidesToShow: 1,
      slidesToScroll: 1
    };

    return (
      <div className="PricingContainer">
        <Slider {...settings}>
          <div>
            <BasicCarePlan />
          </div>
          <div>
            <LawnCarePlan />
          </div>
          <div>
            <PrimeCarePlan />
          </div>
          <div>
            <GrandCarePlan />
          </div>
        </Slider>
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    /*  ings: state.burgerBuilder.ingredients,
        price: state.burgerBuilder.totalPrice,
        error: state.burgerBuilder.error, 
        isAuthenticated: state.auth.token !== null */
  };
};

const mapDispatchToProps = dispatch => {
  return {
    /* onIngredientAdded: (ingName) => dispatch(actions.addIngredient(ingName)),
        onIngredientRemoved: (ingName) => dispatch(actions.removeIngredient(ingName)),
        onInitIngredients: () => dispatch(actions.initIngredients()),
        onInitPurchase: () => dispatch(actions.purchaseInit()), 
        onSetAuthRedirectPath: (path) => dispatch(actions.setAuthRedirectPath(path))*/
  };
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(PricingContainer);
