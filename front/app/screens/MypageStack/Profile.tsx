import { useState, useEffect } from "react";
import BtnBox from "../../components/BtnBox";
import styled from "styled-components/native";
import {
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";
import { Alert, Text } from "react-native";
import axios from "axios";
import PasswordController from "../../components/Controller/PasswordController";

// Compoennt _ Profile
const Profile = ({ navigation }: any) => {
  // const
  // Axios url
  const url = "http://k6S101.p.ssafy.io:8080/user";

  // useState
  const [myInfo, setMyInfo] = useState([]);
  const [isUpdate, setIsUpdate] = useState(false);
  const [password, setPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordConfirm, setNewPasswordConfirm] = useState("");

  // Axios
  const getMyInfo = async () => {
    await axios
      .get(url)
      .then((res) => {
        console.log(res);
        if (res.data) {
          setMyInfo(res.data);
        } else {
          Alert.alert("정보 조회 오류");
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // method
  const updatePassword = async () => {
    if (newPassword !== newPasswordConfirm) {
      Alert.alert("새 비밀번호가 일치하는지 확인해주세요.");
      return;
    }
    const data = {
      password: password,
      newPassword: newPassword,
    };
    await axios
      .patch(url, data)
      .then((res) => {
        setIsUpdate(false);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // useEffect
  useEffect(() => {
    getMyInfo();
  }, []);

  return (
    <LayoutContainer>
      {!isUpdate ? (
        <HeaderText>회원정보</HeaderText>
      ) : (
        <HeaderText>비밀번호 수정</HeaderText>
      )}
      <ContentFooterContainer>
        <ContentContainer>
          {!isUpdate ? (
            <Text>정보</Text>
          ) : (
            <>
              <PasswordController
                setPassword={setPassword}
                text="비밀번호"
                value={password}
              />
              <PasswordController
                setPassword={setNewPassword}
                text="새 비밀번호"
                value={newPassword}
              />
              <PasswordController
                setPassword={setNewPasswordConfirm}
                text="새 비밀번호 확인"
                value={newPasswordConfirm}
              />
            </>
          )}
        </ContentContainer>
        <FooterContainer>
          {!isUpdate ? (
            <>
              <BtnBox
                color="blue"
                text="비밀번호 수정"
                navigation={navigation}
                setter={setIsUpdate}
              />
              <BtnBox color="white" text="뒤로" navigation={navigation} />
            </>
          ) : (
            <>
              <BtnBox
                color="blue"
                text="수정 완료"
                navigation={navigation}
                setter={updatePassword}
              />
              <BtnBox
                color="white"
                text="취소"
                navigation={navigation}
                setter={setIsUpdate}
              />
            </>
          )}
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

const ContentContainer = styled.View`
  flex: 1;
  margin: 30px auto;
  width: 80%;
`;

export default Profile;
