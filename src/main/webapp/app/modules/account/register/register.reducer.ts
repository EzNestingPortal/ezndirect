import axios from "axios";

import {
  REQUEST,
  SUCCESS,
  FAILURE
} from "app/shared/reducers/action-type.util";

export const ACTION_TYPES = {
  DEFAULT_UPDATE: "register/DEFAULT_PASSWORD_UPDATE",
  RESET: "register/RESET"
};

const initialState = {
  loading: false,
  defaultUpdateSuccess: false,
  defaultUpdateFailure: false,
  errorMessage: null
};

export type RegisterState = Readonly<typeof initialState>;

// Reducer
export default (state: RegisterState = initialState, action): RegisterState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.DEFAULT_UPDATE):
      return {
        ...state,
        loading: true
      };
    case FAILURE(ACTION_TYPES.DEFAULT_UPDATE):
      return {
        ...initialState,
        defaultUpdateFailure: true,
        errorMessage: action.payload.response.data.errorKey
      };
    case SUCCESS(ACTION_TYPES.DEFAULT_UPDATE):
      return {
        ...initialState,
        defaultUpdateSuccess: true
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
export const handleRegister = (
  username,
  email,
  newPassword,
  langKey = "EN"
) => ({
  type: ACTION_TYPES.DEFAULT_UPDATE,
  payload: axios.post("/api/account/update-default-password", {
    email,
    newPassword,
    langKey
  }),
  meta: {
    successMessage:
      "Registration saved! Please check your email for confirmation."
  }
});

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
