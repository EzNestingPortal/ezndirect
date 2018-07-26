import React from "react";

import "./BuildControl.scss";

const buildcontrol = props => {
  let inputElement = null;
  const inputClasses = ["SelectElement"];

  inputElement = (
    <select
      className={inputClasses.join(" ")}
      value={props.value}
      onChange={props.changed}
    >
      {props.elementconfig.options.map(option => (
        <option key={option.value} value={option.value}>
          {option.displayValue}
        </option>
      ))}
    </select>
  );

  return (
    <div>
      <div className="Label">{props.label}</div>
      {inputElement}
    </div>
  );
};

export default buildcontrol;
