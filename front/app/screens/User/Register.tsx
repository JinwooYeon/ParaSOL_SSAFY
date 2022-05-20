import { useEffect, useState } from "react";
import styled from "styled-components/native";
import IdController from "../../components/Controller/IdController";
import PasswordController from "../../components/Controller/PasswordController";
import NameController from "../../components/Controller/NameController";
import {
  LayoutContainer,
  HeaderText,
  FooterContainer,
  BtnText,
} from "../styled";
import axios from "axios";
import BtnBox from "../../components/BtnBox";
import { Alert, View } from "react-native";

interface PropsType {
  // stack navigation
  navigation: any;
}

// Component _ Register
const Register: React.FC<PropsType> = ({ navigation }) => {
  // const
  // Axios 회원가입 url
  const registUrl = "/user/register";
  // Axios 아이디 중복 체크 url
  const idcheckUrl = "/user/idcheck";

  // useState
  const [id, setId] = useState("");
  const [confirmId, setConfirmId] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirm, setPasswordConfirm] = useState("");
  const [name, setName] = useState("");
  const [idCheck, setIdcheck] = useState(false);

  // Axios
  // 회원가입
  const onSubmit = async () => {
    // [Error] 빈 입력값
    if (!id) {
      Alert.alert("아이디를 입력해주세요.");
      console.log("아이디를 입력해주세요.");
      return;
    }
    if (!password) {
      Alert.alert("비밀번호를 입력해주세요.");
      console.log("비밀번호를 입력해주세요.");
      return;
    }
    if (!passwordConfirm) {
      Alert.alert("비밀번호 확인을 입력해주세요.");
      console.log("비밀번호 확인을 입력해주세요.");
      return;
    }
    if (!name) {
      Alert.alert("이름을 입력해주세요.");
      console.log("이름을 입력해주세요.");
      return;
    }
    // [Error] 빈 입력값
    if (password !== passwordConfirm) {
      Alert.alert("비밀번호 확인이 일치하지 않습니다.");
      console.log("비밀번호 확인이 일치하지 않습니다.");
      return;
    }
    // [Error] 아이디 중복 확인 안함
    if (!idCheck) {
      Alert.alert("중복된 아이디가 있는지 확인해주세요.");
      console.log("중복된 아이디가 있는지 확인해주세요.");
      return;
    }
    const data = {
      id: id,
      password: password,
      name: name,
    };
    await axios
      .post(registUrl, data)
      .then((res) => {
        if (res.data) {
          setId("");
          setPassword("");
          setPasswordConfirm("");
          setName("");
          Alert.alert("회원가입 성공!");
          navigation.navigate("Login");
        }
      })
      .catch((err) => {
        console.log(err);
        Alert.alert("에러가 발생했습니다. 잠시 후에 다시 시도해주세요.");
      });
  };

  // 아이디 중복 체크
  const checkId = async () => {
    if (!id) {
      Alert.alert("아이디를 입력해주세요.");
      console.log("아이디를 입력해주세요.");
      return;
    }
    await axios
      .post(idcheckUrl, { id: id })
      .then((res) => {
        Alert.alert("사용가능한 아이디입니다.");
        console.log("사용가능한 아이디입니다.");
        setIdcheck(true);
        setConfirmId(id);
      })
      .catch((err) => {
        // [Error] 존재하는 아이디
        if (err.response.status === 400) {
          Alert.alert("이미 존재하는 아이디입니다.");
          console.log("이미 존재하는 아이디입니다.");
          setIdcheck(false);
          setConfirmId("");
        }
        console.log(err);
      });
  };

  // method
  const handleIdCheck = () => {
    if (confirmId !== id) {
      setIdcheck(false);
    }
  };

  // useEffect
  useEffect(() => {
    handleIdCheck();
  }, [id]);

  return (
    <LayoutContainer>
      <HeaderText>회원가입</HeaderText>
      <ContentContainer>
        <View style={{ marginBottom: 80 }}>
          <IdController setId={setId} text="아이디" value={id} />
          {!idCheck ? (
            <BtnBox
              color="blue"
              text="아이디 중복 체크"
              setter={checkId}
            ></BtnBox>
          ) : (
            <View
              style={{
                justifyContent: "center",
                alignItems: "center",
                paddingHorizontal: "15%",
                paddingVertical: "2%",
                backgroundColor: "grey",
                marginVertical: "2%",
              }}
            >
              <BtnText white={false}>중복 확인됨</BtnText>
            </View>
          )}
          <PasswordController
            setPassword={setPassword}
            text="비밀번호"
            value={password}
          />
          <PasswordController
            setPassword={setPasswordConfirm}
            text="비밀번호 확인"
            value={passwordConfirm}
          />
          <NameController setName={setName} text="이름" value={name} />
        </View>
        <FooterContainer>
          <BtnBox
            color="blue"
            text="회원가입"
            setter={onSubmit}
            navigation={navigation}
          ></BtnBox>
          <BtnBox color="white" text="뒤로" navigation={navigation}></BtnBox>
        </FooterContainer>
      </ContentContainer>
    </LayoutContainer>
  );
};

const ContentContainer = styled.View`
  flex: 1;
  margin: 20px auto;
  width: 80%;
`;

export default Register;
