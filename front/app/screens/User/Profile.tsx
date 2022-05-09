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

const Profile = ({ navigation }: any) => {
  const [myInfo, setMyInfo] = useState([]);
  const [isUpdate, setIsUpdate] = useState(false);
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
      {!isUpdate ? (
        <HeaderText>회원정보</HeaderText>
      ) : (
        <HeaderText>회원정보 수정</HeaderText>
      )}

      <ContentFooterContainer>
        <ContentContainer>
          {!isUpdate ? (
            myInfo.map((info) => {
              return <Text>{info}</Text>;
            })
          ) : (
            <Text>정보 수정</Text>
          )}
        </ContentContainer>
        <FooterContainer>
          {!isUpdate ? (
            <>
              <BtnBox
                color="blue"
                text="정보 수정"
                navigation={navigation}
                setIsUpdate={setIsUpdate}
              />
              <BtnBox
                color="blue"
                text="비밀번호 수정"
                navigation={navigation}
              />
            </>
          ) : (
            <BtnBox
              color="blue"
              text="수정 완료"
              navigation={navigation}
              setIsUpdate={setIsUpdate}
            />
          )}
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Profile;
