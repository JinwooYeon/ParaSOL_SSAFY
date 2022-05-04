import styled from "styled-components/native";
import { mainBlue, balanceGrey } from "../color";

// Container
export const LayoutContainer = styled.View`
  flex: 1;
  margin-right: 5%;
  margin-left: 5%;
`;
export const HeaderContainer = styled.View`
  /* background-color: red; */
  height: 120px;
  margin-bottom: 12%;
`;
export const ContentContainer = styled.View`
  /* background-color: green; */
  height: 360px;
  margin-bottom: 12%;
  margin-right: 2%;
  margin-left: 2%;
`;
export const FooterContainer = styled.View`
  /* background-color: blue; */
  height: 120px;
  margin-bottom: 12%;
`;

// Header _ BalanceBox
export const HeaderText = styled.Text`
  color: ${mainBlue};
  font-size: 25px;
  font-weight: bold;
  margin-bottom: 3%;
`;
export const BalanceContainer = styled.View`
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 96%;
  height: 60px;
  border-radius: 12px;
  background-color: ${balanceGrey};
  margin-right: 2%;
  margin-left: 2%;
  padding-right: 5%;
  padding-left: 5%;
`;
export const Balance = styled.Text`
  font-size: 23px;
  font-weight: bold;
`;

// Content _ PriceBox
export const PriceInputContainer = styled.View`
  /* background-color: orange; */
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  border-bottom-color: black;
  border-bottom-width: 2px;
  margin-bottom: 2%;
`;
export const PriceInputText = styled.Text`
  font-size: 25px;
  font-weight: bold;
  padding-right: 1%;
  padding-left: 1%;
`;
export const PriceInput = styled.TextInput`
  height: 30px;
  font-size: 25px;
`;
export const PriceInputTextContainer = styled.View`
  flex-direction: row;
  justify-content: space-between;
`;
export const PriceBtnContainer = styled.View`
  /* background-color: pink; */
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;
export const PriceBtn = styled.TouchableOpacity`
  align-items: center;
  background-color: white;
  padding-right: 10%;
  padding-left: 10%;
  border-color: black;
  border-width: 1px;
`;
export const PriceBtnText = styled.Text`
  font-size: 17px;
  color: ${mainBlue};
`;
