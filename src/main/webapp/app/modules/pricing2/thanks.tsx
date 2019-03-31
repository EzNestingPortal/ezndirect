import React, { Component } from "react";

import { StripeProvider } from "react-stripe-elements";

import ThanksCheckout from "app/modules/pricing2/ThanksCheckout";

import "./thanks.scss";

export interface IEnrollProps {
  name1: string;
}
class Thanks extends Component<IEnrollProps> {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <StripeProvider apiKey="pk_live_KSvXHLeXD1qJ1JO6AgqhqhsP">
        <ThanksCheckout />
      </StripeProvider>
    );
  }
}

export default Thanks;
