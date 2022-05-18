import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";

// Component _ Oauth
const Oauth = ({ navigation }: any) => (
  <LayoutContainer>
    <HeaderText>OTP 정보 등록</HeaderText>
    <ContentFooterContainer>
      <ContentContainer></ContentContainer>
      <FooterContainer>
        <BtnBox color="white" text="뒤로" navigation={navigation} />
      </FooterContainer>
    </ContentFooterContainer>
  </LayoutContainer>
);

export default Oauth;
