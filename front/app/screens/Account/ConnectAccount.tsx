import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";

const ConnectAccount = ({ navigation }: any) => (
  <LayoutContainer>
    <HeaderText>계좌 관리하기</HeaderText>
    <ContentFooterContainer>
      <ContentContainer></ContentContainer>
      <FooterContainer>
        <BtnBox color="white" text="뒤로" navigation={navigation} />
      </FooterContainer>
    </ContentFooterContainer>
  </LayoutContainer>
);

export default ConnectAccount;
