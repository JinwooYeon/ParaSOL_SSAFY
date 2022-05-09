import { useState, useEffect } from "react";
import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";
import { Text, TextInput } from "react-native";
import axios from "axios";

const UpdateProfile = ({ navigation }: any) => {
  const [myInfo, setMyInfo] = useState([]);
  const url = "http://k65101.p.ssafy.io:8080/user";

  const getMyInfo = async () => {
    await axios
      .get(url)
      .then((res) => {
        console.log(res);
        setMyInfo(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    getMyInfo();
  }, []);

  return (
    <LayoutContainer>
      <HeaderText>회원정보</HeaderText>
      <ContentFooterContainer>
        <ContentContainer>
          {myInfo.map((info) => {
            return <TextInput>{info}</TextInput>;
          })}
        </ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="수정 완료" navigation={navigation} />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default UpdateProfile;
