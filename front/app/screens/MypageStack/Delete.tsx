import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";

const Delete = ({ navigation }: any) => (
  <LayoutContainer>
    <HeaderText>회원탈퇴</HeaderText>
    <ContentFooterContainer>
      <ContentContainer></ContentContainer>
      <FooterContainer>
        <BtnBox color="red" text="회원 탈퇴" navigation={navigation} />
        <BtnBox color="white" text="뒤로" navigation={navigation} />
      </FooterContainer>
    </ContentFooterContainer>
  </LayoutContainer>
);

export default Delete;
