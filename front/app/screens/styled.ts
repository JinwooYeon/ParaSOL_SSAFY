import styled from "styled-components/native";
import { mainBlue, balanceGrey } from "../color";

// Container
export const LogoContainer = styled.SafeAreaView`
  /* background-color: black; */
  margin-top: 10%;
  margin-bottom: 4%;
  margin-left: 5%;
  flex-direction: row;
`;
export const BoxContainer = styled.SafeAreaView`
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
export const LayoutContainer = styled.SafeAreaView`
  /* background-color: teal; */
  flex: 1;
  margin-right: 5%;
  margin-left: 5%;
`;
export const HeaderContainer = styled.View`
  /* background-color: red; */
  height: 23%;
  margin-bottom: 2%;
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
  font-size: 20px;
  font-weight: bold;
  margin-right: 2%;
  margin-left: 2%;
`;

// Content _ Confirm
export const ConfirmContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  margin: 20%;
`;
export const ConfirmTargetContainer = styled.View`
  width: 100%;
  margin-bottom: 5%;
  border-width: 2px;
  border-radius: 5px;
  padding: 1% 5%;
`;
export const ConfirmTargetText = styled.Text`
  font-size: 18px;
`;
export const ConfirmBtnContainer = styled.View`
  width: 100%;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;
export const ConfirmBtnTouchableOpacity = styled.TouchableOpacity<{
  ok: boolean;
}>`
  border-width: 2px;
  border-color: ${(props) => (props.ok ? mainBlue : "red")};
  border-radius: 5px;
  padding: 1% 15%;
`;
export const ConfirmBtnText = styled.Text`
  font-size: 25px;
  font-weight: bold;
`;

// Content _ Home _ Transaction
export const TransactionContainer = styled.View`
  width: 100%;
  height: auto;
  border-radius: 12px;
  background-color: ${balanceGrey};
  padding-top: 5%;
  padding-right: 3%;
  padding-bottom: 5%;
  padding-left: 3%;
  justify-content: space-between;
  align-items: center;
  flex-direction: row;
`;
export const TransactionTextInput = styled.TextInput`
  /* background-color: green; */
  width: 90%;
  height: auto;
  font-size: 25px;
`;
export const TransactionTextDelTouchableOpacity = styled.TouchableOpacity``;
export const TransactionTextDel = styled.Text`
  font-size: 28px;
  font-weight: bold;
  color: grey;
`;

// Content _ Home
export const QRcodeContainer = styled.View`
  border-width: 2px;
  border-radius: 10px;
  border-color: ${mainBlue};
  justify-content: space-between;
  align-items: center;
  /* margin-top: 18%; */
  margin-top: 8%;
  /* margin-bottom: 18%; */
  padding-top: 5%;
  padding-bottom: 5%;
`;
export const QRcodeInfoContainer = styled.View`
  justify-content: center;
  align-items: center;
  margin-top: 5%;
`;
export const QRcodeInfoName = styled.TouchableOpacity``;
export const QRcodeInfoNameText = styled.Text`
  font-size: 25px;
  color: ${mainBlue};
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
  margin-top: 10%;
  margin-bottom: 2%;
`;
export const PriceInputText = styled.Text`
  font-size: 20px;
  font-weight: bold;
  padding-right: 1%;
  padding-left: 1%;
  padding-bottom: 2%;
`;
export const PriceInput = styled.TextInput`
  width: 220px;
  font-size: 20px;
  padding-bottom: 2%;
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

// Content _ Benefit
export const BenefitContainer = styled.SafeAreaView`
  /* background-color: grey; */
  flex: 0.8;
  justify-content: center;
  align-items: center;
`;
export const BenefitText = styled.Text`
  font-size: 25px;
`;

// Content _ MypageMenu
export const MypageMenuContainer = styled.View`
  /* background-color: red; */
  flex: 1;
  margin-top: 12%;
  margin-bottom: 22%;
  padding-right: 5%;
  padding-left: 5%;
  justify-content: space-between;
`;
export const MypageMenuTextContainer = styled.View`
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5%;
`;
export const MypageMenuBtn = styled.TouchableOpacity``;
export const MypageMenuText = styled.Text<{ able?: boolean }>`
  font-size: 23px;
  font-weight: bold;
  color: ${(props) => (props.able ? "black" : "grey")};
`;

// Footer _ Button
export const BtnContainer = styled.View`
  margin-top: 2%;
  margin-bottom: 2%;
`;
export const BtnContainerRow = styled.View`
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
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
  border-color: ${({ color }) => (color === "red" ? "red" : mainBlue)};
  border-width: ${({ color }) => (color === "white" ? "1px" : "1px")};
  justify-content: center;
  align-items: center;
  padding-top: 2%;
  padding-right: 15%;
  padding-bottom: 2%;
  padding-left: 15%;
`;
export const BtnText = styled.Text<{ white: boolean }>`
  font-size: 20px;
  color: ${({ white }) => (white ? "black" : "white")};
`;
