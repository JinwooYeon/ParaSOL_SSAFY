import { useState, useEffect } from "react";
import BtnBox from "../../components/BtnBox";
import styled from "styled-components/native";
import {
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";
import { Alert, Text, View } from "react-native";
import axios from "axios";
import PasswordController from "../../components/Controller/PasswordController";
import AsyncStorage from "@react-native-async-storage/async-storage";

interface PropsType {
  // 새로운 인증 토큰 발급
  getNewToken: () => void;
  // navigation
  navigation: any;
}

// Compoennt _ Profile
const Profile: React.FC<PropsType> = ({ getNewToken, navigation }) => {
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
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios
      .get(url, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      })
      .then((res) => {
        if (!res.data) {
          Alert.alert("정보 조회 오류");
        }
        setMyInfo(res.data);
      })
      .catch((err) => {
        if (err.response.status === 401) {
          getNewToken?.();
        }
      });
  };

  // method
  const updatePassword = async () => {
    // [Error] 빈 입력값
    if (!password) {
      Alert.alert("비밀번호를 입력해주세요.");
      console.log("비밀번호를 입력해주세요.");
      return;
    }
    if (!newPassword) {
      Alert.alert("새 비밀번호를 입력해주세요.");
      console.log("새 비밀번호를 입력해주세요.");
      return;
    }
    if (!newPasswordConfirm) {
      Alert.alert("새 비밀번호 확인을 입력해주세요.");
      console.log("새 비밀번호 확인을 입력해주세요.");
      return;
    }
    // [Error] 비밀번호와 비밀번호 확인이 일치하지 않음
    if (newPassword !== newPasswordConfirm) {
      Alert.alert("새 비밀번호가 일치하는지 확인해주세요.");
      console.log("새 비밀번호가 일치하는지 확인해주세요.");
      return;
    }
    const data = {
      password: password,
      newPassword: newPassword,
    };
    await axios
      .patch(url, data)
      .then((res) => {
        console.log(res);
        Alert.alert("비밀번호가 수정되었습니다.");
        console.log("비밀번호가 수정되었습니다.");
        setIsUpdate(false);
      })
      .catch(async (err) => {
        console.log(err);
        // [Error] token 값 만료
        if (err.response.status === 401 && (await getNewToken?.())) {
          updatePassword();
        }
        // [Error] 잘못된 입력값
        if (err.response.status === 400) {
          Alert.alert("기존 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
          console.log("기존 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
        }
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
            // <Text>정보</Text>
            Object.entries(myInfo).map((info) => {
              if (info[0] !== "password") {
                return (
                  <ContentBox key={info[0]}>
                    <Text
                      style={{
                        fontSize: 25,
                      }}
                    >
                      {info[0]}{" "}
                    </Text>
                    <Text style={{ fontSize: 30, fontWeight: "bold" }}>
                      {info[1]}
                    </Text>
                  </ContentBox>
                );
              }
            })
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

const ContentBox = styled.View`
  flex-direction: row;
  align-items: flex-end;
  justify-content: space-between;
  margin: 10px 0px;
`;

export default Profile;
