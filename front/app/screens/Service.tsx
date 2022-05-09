import BtnBox from "../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "./styled";

const Service = ({ navigation }: any) => (
  <LayoutContainer>
    <HeaderText>고객 문의</HeaderText>
    <ContentFooterContainer>
      <ContentContainer></ContentContainer>
      <FooterContainer>
        <BtnBox color="white" text="뒤로" navigation={navigation} />
      </FooterContainer>
    </ContentFooterContainer>
  </LayoutContainer>
);

export default Service;
