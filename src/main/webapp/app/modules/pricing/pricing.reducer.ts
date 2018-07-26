import axios from "axios";

import {
  REQUEST,
  SUCCESS,
  FAILURE
} from "app/shared/reducers/action-type.util";

export const ACTION_TYPES = {
  GET_MY_PRICE: "pricing/GET_MY_PRICE",
  RESET: "pricing/RESET"
};

const initialState = {
  loading: false,
  pricingSuccess: false,
  pricingFailure: false,
  showResults: false,
  errorMessage: null,
  price1: 45,
  price2: 0,
  subscriptions: []
};

export type PricingState = Readonly<typeof initialState>;

// Reducer
export default (state: PricingState = initialState, action): PricingState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.GET_MY_PRICE):
      return {
        ...state,
        loading: true
      };
    case FAILURE(ACTION_TYPES.GET_MY_PRICE):
      return {
        ...initialState,
        pricingFailure: true,
        errorMessage: action.payload.response.data.errorKey
      };
    case SUCCESS(ACTION_TYPES.GET_MY_PRICE):
      return {
        ...initialState,
        pricingSuccess: true,
        showResults: true,
        subscriptions: action.payload.data
      };

    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

// Actions
export const handleGetMyPrice = (lotSize, floorSize, floors) => ({
  type: ACTION_TYPES.GET_MY_PRICE,
  payload: axios.get("/api/pricing", {
    params: {
      lotSize: lotSize,
      floorSize: floorSize,
      floors: floors
    }
  }) /* .then(function (response) {
    console.log("***RESPONSE***")
    console.log(response);
    //data => this.setState({subscription: response.data});
  }).catch(function (error) {
     console.log("***ERROR***")
     console.log(error);
  })  */
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
