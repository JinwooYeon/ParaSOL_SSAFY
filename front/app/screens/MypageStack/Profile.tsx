import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";

const Profile = ({ navigation }: any) => (
  <LayoutContainer>
    <HeaderText>회원정보</HeaderText>
    <ContentFooterContainer>
      <ContentContainer></ContentContainer>
      <FooterContainer>
        <BtnBox color="blue" text="정보 수정" navigation={navigation} />
        <BtnBox color="blue" text="수정 완료" navigation={navigation} />
        <BtnBox color="blue" text="비밀번호 수정" navigation={navigation} />
        <BtnBox color="white" text="뒤로" navigation={navigation} />
      </FooterContainer>
    </ContentFooterContainer>
  </LayoutContainer>
);

export default Profile;
