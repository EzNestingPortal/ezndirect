import React from "react";
import { Elements } from "react-stripe-elements";

import InjectedCheckoutForm from "app/modules/pricing2/checkout1";

class ThanksCheckout extends React.Component {
  render() {
    return (
      <Elements>
        <InjectedCheckoutForm />
      </Elements>
    );
  }
}

export default ThanksCheckout;
