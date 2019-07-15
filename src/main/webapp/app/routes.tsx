import React from "react";
import { Switch } from "react-router-dom";
import Loadable from "react-loadable";

import Login from "app/modules/login/login";
import Register from "app/modules/account/register/register";
import Activate from "app/modules/account/activate/activate";
import PasswordResetInit from "app/modules/account/password-reset/init/password-reset-init";
import PasswordResetFinish from "app/modules/account/password-reset/finish/password-reset-finish";
import Logout from "app/modules/login/logout";
import Home from "app/modules/home/home";
import Entities from "app/entities";
import PrivateRoute from "app/shared/auth/private-route";
import ErrorBoundaryRoute from "app/shared/error/error-boundary-route";
import Route from "app/shared/error/error-boundary-route";
import { AUTHORITIES } from "app/config/constants";

import Pricing from "app/modules/pricing/pricing";
import Pricing2 from "app/modules/pricing2/pricing2";
import Enroll from "app/modules/pricing2/enroll";
import Enroll1 from "app/modules/pricing2/enroll1";
import Thanks from "app/modules/pricing2/thanks";
import Thanks1 from "app/modules/pricing2/thanks1"; /*}); */ //"app/modules/account"), //"app/modules/administration"), //loading: () => <div>loading ...</div> //loading: () => <div>loading ...</div> //}); */

// tslint:disable:space-in-parens
/* const Account = Loadable({
  loader: () => import(/* webpackChunkName: "account" */ /*const Admin = Loadable({
  loader: () =>
    import(/* webpackChunkName: "administration" */ // tslint:enable

const Routes = () => (
  <div className="view-routes">
    <ErrorBoundaryRoute path="/login" component={Login} />
    <Switch>
      {/* <ErrorBoundaryRoute path="/logout" component={Logout} /> */}
      <ErrorBoundaryRoute path="/register" component={Register} />
      {/*<ErrorBoundaryRoute path="/pricing" component={Pricing} /> */}
      <ErrorBoundaryRoute path="/pricing2" component={Pricing2} />
      <ErrorBoundaryRoute path="/enroll" component={Enroll} />
      {/* <ErrorBoundaryRoute path="/onetime/:key?" component={Enroll1} /> */}
      <ErrorBoundaryRoute path="/thank-you" component={Thanks} />
      {/* <ErrorBoundaryRoute path="/thank-you1" component={Thanks1} /> */}
      {/* <PrivateRoute
        path="/enroll"
        component={Enroll}
        hasAnyAuthorities={[AUTHORITIES.ADMIN]}/>
      <PrivateRoute
        path="/thanks"
        component={Thanks}
        hasAnyAuthorities={[AUTHORITIES.ADMIN]}/> */}

      {/* <ErrorBoundaryRoute path="/activate/:key?" component={Activate} />
      <ErrorBoundaryRoute path="/reset/request" component={PasswordResetInit} />
      <ErrorBoundaryRoute
        path="/reset/finish/:key?"
        component={PasswordResetFinish}
      /> */}
      {/* <PrivateRoute
        path="/admin"
        component={Admin}
        hasAnyAuthorities={[AUTHORITIES.ADMIN]}
      />
      <PrivateRoute
        path="/account"
        component={Account}
        hasAnyAuthorities={[AUTHORITIES.ADMIN, AUTHORITIES.USER]}
      />
      <PrivateRoute
        path="/entity"
        component={Entities}
        hasAnyAuthorities={[AUTHORITIES.USER]}
      /> */}
      <ErrorBoundaryRoute path="/" component={Pricing2} />
    </Switch>
  </div>
);

export default Routes;
