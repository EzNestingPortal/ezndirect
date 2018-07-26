import React, { Component } from "react";
import Collapsible from "react-collapsible";

import "./plan.scss";

export interface IPlan {
  circle: string;
  heading: string;
  header: string;

  planName: string;

  discountedPrice: number;
  originalAmount: number;
  yearlySavings: number;
  idleMonths: string;
  services: any;
}

class Plan extends Component<IPlan, null> {
  render() {
    let details = (
      <div className="PlanContainer">
        <div className="Plan">
          <div className={this.props.circle}>
            <h1>${this.props.discountedPrice}</h1>
            <h3>Pay Bi-weekly</h3>
          </div>
        </div>
        <div className="BasicPlanType">
          <h1 className={this.props.heading}>{this.props.planName}</h1>
          <h4>Originally ${this.props.originalAmount}</h4>
        </div>
        <div className="PlanDetails">
          <Collapsible
            trigger="Benefits you get"
            triggerClassName={this.props.header}
            triggerOpenedClassName={this.props.header}
            open={window.innerWidth > 600 ? true : false}
          >
            <p>Yearly SAVINGS ${this.props.yearlySavings}</p>
            <p>No Charges in {this.props.idleMonths}</p>

            {this.props.services.map((service, j) => (
              <p key={`Service${j}`}>
                {service.serviceName} ({service.frequency})
              </p>
            ))}

            {/* <p>Twice-monthly Lawn Mowing and Blowing</p>
            <p>Weed-eating (twice-monthly)</p>
            <p>Edging and Grass Trimming (twice-monthly)</p>
            <p>Lawn Fertilizer (thrice a year)</p>
            <p>Weed Control (thrice a year)</p>
            <p>--</p>
            <p>--</p>
            <p>--</p> */}
          </Collapsible>
        </div>
      </div>
    );
    return <div className="PlanContainer">{details}</div>;
  }
}

const mapStoreToProps = state => {
  return {
    price1: state.pricing.price1
  };
};

export default Plan;
