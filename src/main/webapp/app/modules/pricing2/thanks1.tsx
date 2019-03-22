import React, { Component } from "react";

import { StripeProvider } from "react-stripe-elements";
import { Container, Row, Col } from "reactstrap";

import ThanksCheckout from "app/modules/pricing2/ThanksCheckout1";

import "./thanks1.scss";

export interface IEnrollProps {
  name1: string;
}
class Thanks extends Component<IEnrollProps> {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <StripeProvider apiKey="pk_test_QseBPReeIxUM1y6tvGHv3VYL">
        <ThanksCheckout />
      </StripeProvider>
    );
  }
}

export default Thanks;
