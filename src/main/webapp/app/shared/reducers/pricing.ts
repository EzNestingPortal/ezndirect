import * as actionTypes from "../../modules/pricing/actions/actionTypes";
import { updateObject } from "../util/Utility";

const initialState = {
  error: null,
  loading: false,
  formIsValid: false,
  price1: 25,
  price2: 40,
  price3: 50,
  price4: 65
};

const getMyPriceSuccess = (state, action) => {
  if (action.planType === 1) {
    return updateObject(state, {
      price1: action.price
    });
  }
  if (action.planType === 2) {
    return updateObject(state, {
      price2: action.price
    });
  }
  if (action.planType === 3) {
    return updateObject(state, {
      price3: action.price
    });
  }
  if (action.planType === 4) {
    return updateObject(state, {
      price4: action.price
    });
  }
};

const getMyPriceFail = (state, action) => {
  return updateObject(state, {
    error: action.error
  });
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case actionTypes.GET_MY_PRICE_SUCCESS:
      return getMyPriceSuccess(state, action);
    case actionTypes.GET_MY_PRICE_FAIL:
      return getMyPriceFail(state, action);
    default:
      return state;
  }
};

export default reducer;
