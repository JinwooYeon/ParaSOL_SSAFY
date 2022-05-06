import styled from "styled-components/native";
import { mainBlue, balanceGrey } from "../color";

// Container
export const LogoContainer = styled.View`
  margin-top: 10%;
  margin-bottom: 5%;
  margin-left: 5%;
  flex-direction: row;
`;
export const BoxContainer = styled.View`
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: auto;
  border-radius: 12px;
  background-color: ${balanceGrey};
  margin-bottom: 5%;
  padding-top: 5%;
  padding-right: 3%;
  padding-bottom: 5%;
  padding-left: 3%;
`;
export const LayoutContainer = styled.View`
  flex: 1;
  margin-right: 5%;
  margin-left: 5%;
`;
export const HeaderContainer = styled.View`
  /* background-color: red; */
  height: 110px;
  margin-bottom: 8%;
`;
export const ContentFooterContainer = styled.View`
  /* background-color: black; */
  justify-content: space-between;
  flex: 1;
`;
export const ContentContainer = styled.View`
  /* background-color: green; */
`;
export const FooterContainer = styled.View`
  /* background-color: blue; */
  margin-bottom: 5%;
`;

// Logo
export const LogoText = styled.Text<{ blue: boolean }>`
  color: ${(props) => (props.blue ? mainBlue : "black")};
  font-size: 35px;
  font-weight: bold;
`;

// Header _ BalanceBox
export const HeaderText = styled.Text`
  color: ${mainBlue};
  font-size: 25px;
  font-weight: bold;
  margin-bottom: 3%;
`;
export const BalanceTextContainer = styled.View`
  flex-direction: row;
  justify-content: space-between;
`;
export const Balance = styled.Text`
  font-size: 23px;
  font-weight: bold;
  margin-right: 2%;
  margin-left: 2%;
`;

// Content _ ConnectedAccount
export const ConnectedAccountContainer = styled(BoxContainer)`
  flex-direction: column;
  align-items: stretch;
`;
export const ConnectedAccountHeaderContainer = styled.View<{ empty: boolean }>`
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: ${({ empty }) => (empty ? "0%" : "3%")};
`;
export const ConnectedAccountHeaderText = styled.Text`
  font-size: 18px;
  font-weight: bold;
`;
export const ConnectedAccountHeaderSetting = styled.TouchableOpacity``;
export const ConnectedAccountHeaderSettingText = styled.Text`
  font-size: 14px;
  color: ${mainBlue};
`;
export const ConnectedAccountContentContainer = styled.View`
  flex-direction: row;
`;
export const ConnectedAccountDetailContainer = styled.View``;
export const ConnectedAccountDetailImg = styled.Image`
  width: 40px;
  height: 40px;
  margin-right: 3%;
`;
export const ConnectedAccountDetailBank = styled.Text`
  font-size: 16px;
  font-weight: bold;
`;
export const ConnectedAccountDetailNum = styled.Text`
  font-size: 14px;
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
  width: 33%;
  border-color: black;
  border-width: 1px;
`;
export const PriceBtnText = styled.Text`
  font-size: 17px;
  color: ${mainBlue};
`;

// Footer _ Button
export const BtnContainer = styled.View`
  margin-top: 2%;
  margin-bottom: 2%;
`;
export const Btn = styled.TouchableOpacity<{ color: string }>`
  background-color: ${({ color }) => {
    if (color === "blue") {
      return mainBlue;
    } else if (color === "red") {
      return "red";
    } else if (color === "white") {
      return "white";
    }
  }};
  border-color: ${mainBlue};
  border-width: ${({ color }) => (color === "white" ? "1px" : "0px")};
  justify-content: center;
  align-items: center;
  padding-top: 2%;
  padding-bottom: 2%;
`;
export const BtnText = styled.Text<{ white: boolean }>`
  font-size: 20px;
  color: ${({ white }) => (white ? "black" : "white")};
`;
