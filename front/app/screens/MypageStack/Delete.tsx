import { Text } from "react-native";
import BtnBox from "../../components/BtnBox";
import styled from "styled-components/native";
import {
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

interface PropsType {
  navigation: any;
  setLogin: (a: boolean) => void;
}

// Component _ Delete
const Delete: React.FC<PropsType> = ({ navigation, setLogin }) => {
  // const
  // Axios url
  const url = "http://k6s101.p.ssafy.io:8080/user";

  // Axios
  const deleteUser = async () => {
    await axios
      .delete(url)
      .then((res) => {
        setLogin(false);
        AsyncStorage.clear();
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
          <Text style={{ fontSize: 20 }}>정말 탈퇴하시겠습니까?</Text>
          <Text style={{ fontSize: 15 }}>
            삭제된 정보는 되돌릴 수 없습니다.
          </Text>
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

const ContentContainer = styled.View`
  flex: 1;
  justify-content: center;
  align-items: center;
  margin-bottom: 90px;
  text-align: center;
`;

export default Delete;
