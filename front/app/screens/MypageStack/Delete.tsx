import { Text } from "react-native";
import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";
import axios from "axios";

const Delete = ({ navigation }: any) => {
  const url = "http://k65101.p.ssafy.io:8080/user";

  const deleteUser = async () => {
    await axios
      .delete(url)
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <LayoutContainer>
      <HeaderText>회원탈퇴</HeaderText>
      <ContentFooterContainer>
        <ContentContainer>
          <Text>정말 탈퇴하시겠습니까? 삭제된 정보는 되돌릴 수 없습니다.</Text>
        </ContentContainer>
        <FooterContainer>
          <BtnBox
            color="red"
            text="회원 탈퇴"
            navigation={navigation}
            setter={deleteUser}
          />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};
export default Delete;
