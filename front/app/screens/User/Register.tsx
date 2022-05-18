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
  const registUrl = "http://k6S101.p.ssafy.io:8080/user/register";
  // Axios 아이디 중복 체크 url
  const idcheckUrl = "http://k6S101.p.ssafy.io:8080/user/idcheck";

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
    if (password !== passwordConfirm) {
      Alert.alert("비밀번호가 일치하지 않습니다.");
      return;
    }
    if (!idCheck) {
      Alert.alert("중복된 아이디가 있는지 확인해주세요.");
    }
    const data = {
      id: id,
      password: password,
      name: name,
    };
    await axios
      .post(registUrl, data)
      .then((res) => {
        if (idCheck && res.data) {
          setId("");
          setPassword("");
          setPasswordConfirm("");
          setName("");
        } else {
          Alert.alert("입력 정보를 확인해주세요.");
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
      return;
    }
    await axios
      .post(idcheckUrl, { id: id })
      .then((res) => {
        if (res.data) {
          Alert.alert("사용가능한 아이디입니다.");
          setIdcheck(true);
          setConfirmId(id);
        } else {
          Alert.alert("이미 존재하는 아이디입니다.");
          setIdcheck(false);
          setConfirmId("");
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // method
  const handleIdCheck = () => {
    console.log(id);
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
