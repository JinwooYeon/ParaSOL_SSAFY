import axios from "axios";
import { useEffect, useState } from "react";
import { Text } from "react-native";
import BtnBox from "../../components/BtnBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";

// Component _ ConnectAccount
const ConnectAccount = ({ navigation }: any) => {
  // const
  // Axios url
  const url = "http://k6S101.p.ssafy.io:8080/account";

  // useState
  const [myAccounts, setMyAccounts] = useState([]);

  // Axios
  const getMyAccounts = async () => {
    await axios
      .get(url)
      .then((res) => {
        setMyAccounts(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // useEffect
  useEffect(() => {
    getMyAccounts();
  }, []);

  return (
    <LayoutContainer>
      <HeaderText>계좌 관리하기</HeaderText>
      <ContentFooterContainer>
        <ContentContainer>
          {myAccounts &&
            myAccounts.map((account: any) => {
              return (
                <>
                  <Text>bankName: {account.bankName}</Text>
                  <Text>id: {account.id}</Text>
                  <Text>password: {account.password}</Text>
                </>
              );
            })}
        </ContentContainer>
        <FooterContainer>
          <BtnBox color="white" text="뒤로" navigation={navigation} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default ConnectAccount;
