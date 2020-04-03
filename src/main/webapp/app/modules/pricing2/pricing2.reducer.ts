import axios from 'axios';
import { Storage } from 'react-jhipster';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

export const ACTION_TYPES = {
  GET_MY_PRICE: 'pricing/GET_MY_PRICE',
  RESET: 'pricing/RESET',
  CREATE_ACCOUNT_IN_DB: 'save/CREATE_ACCOUNT_IN_DB',
  CREATE_ACCOUNT_IN_RAZORSYNC: 'enroll/CREATE_ACCOUNT_IN_RAZORSYNC',
  LOGIN: 'authentication/LOGIN',
  GET_SESSION: 'authentication/GET_SESSION'
};

const initialState = {
  loading: false,
  pricingSuccess: false,
  pricingFailure: false,
  showResults: false,
  errorMessage: null,
  razorSuccess: false,
  razorFailure: false,
  registrationSuccess: false,
  registrationFailure: false,
  saveInDbSuccess: false,
  saveInDbFailure: false,
  price1: 45,
  price2: 0,
  subscriptions: [],
  name1: null
};

const AUTH_TOKEN_KEY = 'jhi-authenticationToken';

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
        errorMessage: 'Something went wrong, please try later'
      };
    case SUCCESS(ACTION_TYPES.GET_MY_PRICE):
      return {
        ...initialState,
        pricingSuccess: true,
        showResults: true,
        subscriptions: action.payload.data
      };

    case REQUEST(ACTION_TYPES.CREATE_ACCOUNT_IN_DB):
      return {
        ...state,
        loading: true
      };
    case FAILURE(ACTION_TYPES.CREATE_ACCOUNT_IN_DB):
      return {
        ...initialState,
        saveInDbFailure: true,
        errorMessage: action.payload.response.data.errorKey
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACCOUNT_IN_DB):
      return {
        ...initialState,
        saveInDbSuccess: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ACCOUNT_IN_RAZORSYNC):
      return {
        ...state,
        loading: true
      };
    case FAILURE(ACTION_TYPES.CREATE_ACCOUNT_IN_RAZORSYNC):
      return {
        ...initialState,
        razorFailure: true,
        errorMessage: action.payload.response.data.errorKey
      };
    case SUCCESS(ACTION_TYPES.CREATE_ACCOUNT_IN_RAZORSYNC):
      return {
        ...initialState,
        saveInDbSuccess: true,
        razorSuccess: true
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
export const handleGetMyPrice = (lawnSize, propertySize, floors) => ({
  type: ACTION_TYPES.GET_MY_PRICE,
  payload: axios.get('/api/pricing', {
    params: {
      lawnSize: lawnSize,
      propertySize: propertySize,
      floors: floors
    }
  })
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});

export const back = () => ({
  type: ACTION_TYPES.RESET
});

export const handleRegister = (user, headers) => async (dispatchEvent, getState) => {
  const registerResult = await dispatchEvent({
    type: ACTION_TYPES.CREATE_ACCOUNT_IN_DB,
    payload: axios.post('/api/register', user)
  });

  /* const enrollResult = await dispatchEvent({
    type: ACTION_TYPES.CREATE_ACCOUNT_IN_RAZORSYNC,
    payload: axios.post("/api/enroll", user),
    meta: {
      errorMessage:
        "An error occurred while enrolling you, Please try after sometime."
    } 
  }); */
};

export const login = (username, password, rememberMe = false) => async (dispatch, getState) => {
  const result = await dispatch({
    type: ACTION_TYPES.LOGIN,
    payload: axios.post('/api/authenticate', { username, password, rememberMe })
  });
  const bearerToken = result.value.headers.authorization;
  if (bearerToken && bearerToken.slice(0, 7) === 'Bearer ') {
    const jwt = bearerToken.slice(7, bearerToken.length);
    if (rememberMe) {
      Storage.local.set(AUTH_TOKEN_KEY, jwt);
    } else {
      Storage.session.set(AUTH_TOKEN_KEY, jwt);
    }
  }
  dispatch(getSession());
};

export const getSession = () => ({
  type: ACTION_TYPES.GET_SESSION,
  payload: axios.get('/api/account')
});
