import * as actionTypes from "./actionTypes";

export const getMyPriceSuccess = (orderData, price, planType) => {
  return {
    type: actionTypes.GET_MY_PRICE_SUCCESS,
    planType: planType,
    price: price,
    orderData: orderData
  };
};

export const getMyPriceFail = error => {
  return {
    type: actionTypes.GET_MY_PRICE_FAIL,
    error: error
  };
};
const randomPrice = () => {
  return Math.floor(Math.random() * 99);
};

export const getMyPrice = order => {
  /* alert(" Price:: " + order.price
         +"\n Lot Size:: " + order.orderData.lotSize
         +"\n Prperty Size:: " + order.orderData.propSize
         +"\n Storeys:: " + order.orderData.storeys
         +"\n Frequency:: " + order.orderData.frequency); */

  return dispatch => {
    let url = "https://jsonplaceholder.typicode.com/posts/1";

    fetch(url, order.orderData)
      .then(response => {
        const expirationDate = new Date(new Date().getTime() + 10 * 1000);
        dispatch(
          getMyPriceSuccess(order.orderData, randomPrice(), order.planType)
        );
      })
      .catch(err => {
        dispatch(getMyPriceFail(err));
      });
  };
};
