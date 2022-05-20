import axios from "axios";
import { useEffect, useState } from "react";
import BtnBox from "../../components/BtnBox";
import ConnectedAccountBox from "../../components/ConnectedAccountBox";
import {
  Balance,
  BalanceTextContainer,
  BoxContainer,
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderText,
  LayoutContainer,
} from "../styled";
import { Alert, View } from "react-native";
import { TextInputController } from "../../components/Controller/controllerStyled";
import AsyncStorage from "@react-native-async-storage/async-storage";

interface PropsType {
  // 계좌 연결 정보
  bankInfo?: any;
  // 계좌 연결 정보 set
  setBankInfo?: (a: any) => void;
  // 계좌 연결 정보 존재 여부 set
  setEmpty?: (a: boolean) => void;
  // stack navigation
  navigation: any;
  // 새로운 인증 토큰 발급
  getNewToken?: () => Promise<any>;
  // 내 정보 가져오기
  getMyInfo: () => void;
}

// Component _ HasAccount
const HasAccount: React.FC<PropsType> = ({
  navigation,
  bankInfo,
  setEmpty,
}) => {
  // const
  const urlget = "/account/balance";

  const [num, setNum] = useState("0");

  // 세자리 수마다 콤마 찍기
  const onChangeText = (s: string) => {
    s = s.replace(/[^0-9]/g, "");
    if (s === "") s = "0";
    let temp = s.slice(0, 1);
    if (s.length > 1 && temp === "0") {
      s = s.substring(1);
    }
    let rep = s.replace(/,/g, "");
    setNum(rep.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
  };

  // Axios
  const getAccountBalance = async (data: any) => {
    console.log("get");
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "get",
      url: urlget,
      headers: { Authorization: `Bearer ${accessToken}` },
      params: data,
    })
      .then(async (res) => {
        console.log("getAccountBalance");
        let stringBal = res.data.bankAccountBalance + "";
        onChangeText(stringBal);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  // useEffect
  useEffect(() => {
    if (bankInfo.bankNum === null) {
      setEmpty?.(true);
    }
    getAccountBalance({
      bankName: bankInfo.bankName,
      bankAccountNumber: bankInfo.bankNum,
    });
  }, [bankInfo.bankNum]);

  return (
    <>
      <ContentContainer>
        <ConnectedAccountBox
          bankInfo={bankInfo}
          navigation={navigation}
          myPage={true}
        />
        <BoxContainer>
          <Balance>계좌 잔액</Balance>
          <BalanceTextContainer>
            <Balance>{num}</Balance>
            <Balance>원</Balance>
          </BalanceTextContainer>
        </BoxContainer>
      </ContentContainer>
      <FooterContainer>
        <BtnBox
          color="blue"
          text="계좌 수정"
          navigation={navigation}
          setter={() => setEmpty?.(true)}
        />
        <BtnBox color="white" text="뒤로" navigation={navigation} />
      </FooterContainer>
    </>
  );
};

// Component _ HasNotAccount
const HasNotAccount: React.FC<PropsType> = ({
  navigation,
  setEmpty,
  getNewToken,
  bankInfo,
  getMyInfo,
}) => {
  // const
  // Axios url
  const urlPost = "/bank";

  // useState
  // 계좌 연결 정보
  const [data, setData] = useState({
    bankName: "SBJ",
    id: "",
    password: "",
  });

  // Axios
  // 계좌 연결
  const bankPost = async () => {
    console.log("post");
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "post",
      url: urlPost,
      headers: { Authorization: `Bearer ${accessToken}` },
      data,
    })
      .then(async (res) => {
        console.log(res.data);
        await getMyInfo();
        if (bankInfo.bankNum !== null) {
          setEmpty?.(false);
          Alert.alert("계좌 연결 성공!");
        } else {
          Alert.alert("계좌 연결에 실패하였습니다.");
        }
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken?.())) {
          bankPost();
        } else {
          Alert.alert("계좌 연결에 실패하였습니다.");
        }
      });
  };
  // 연결 계좌 삭제
  const bankDel = async () => {
    console.log("del");
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "delete",
      url: urlPost,
      headers: { Authorization: `Bearer ${accessToken}` },
      data: { bankName: "SBJ" },
    })
      .then(async (res) => {
        console.log(res.data);
        console.log("계좌 연결 정보 삭제 성공!");
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken?.())) {
          bankDel();
        } else {
          Alert.alert("계좌 연결에 실패하였습니다.");
        }
      })
      .finally(async () => {
        await bankPost();
      });
  };

  // method
  // const setBankName = (s: string) => {
  //   setData({ ...data, bankName: s });
  // };
  const setId = (s: string) => {
    setData({ ...data, id: s });
  };
  const setPassword = (s: string) => {
    setData({ ...data, password: s });
  };

  // useEffect
  useEffect(() => {
    if (bankInfo.bankNum === null) {
      setEmpty?.(true);
    }
  }, [bankInfo.bankNum]);

  return (
    <>
      <ContentContainer>
        <View>
          <TextInputController
            // onChangeText={setBankName}
            value={`은행명: ${data.bankName}`}
            placeholder="은행명"
            keyboardType="visible-password"
            editable={false}
          />
          <TextInputController
            onChangeText={setId}
            value={data.id}
            placeholder="은행 ID"
            keyboardType="visible-password"
          />
          <TextInputController
            onChangeText={setPassword}
            value={data.password}
            placeholder="은행 Password"
            secureTextEntry={true}
          />
        </View>
      </ContentContainer>
      <FooterContainer>
        <BtnBox
          color="blue"
          text="계좌 연결"
          navigation={navigation}
          // setter={bankInfo.bankNum === null ? bankPost : bankDel}
          setter={bankDel}
        />
        <BtnBox color="white" text="뒤로" navigation={navigation} />
      </FooterContainer>
    </>
  );
};

// Component _ ConnectAccount
const ConnectAccount: React.FC<PropsType> = ({
  navigation,
  bankInfo,
  setBankInfo,
  getNewToken,
  getMyInfo,
}) => {
  // const
  // 계좌 연결 정보 비구조화
  const { bankNum } = bankInfo;
  // 계좌 연결 정보 존재 여부
  const [empty, setEmpty] = useState<boolean>(true);

  // useEffect
  useEffect(() => {
    getMyInfo();
    if (bankNum !== null) {
      setEmpty(false);
    } else {
      setEmpty?.(true);
    }
  }, []);

  return (
    <LayoutContainer>
      <HeaderText>계좌 관리하기</HeaderText>
      <ContentFooterContainer>
        {empty ? (
          <HasNotAccount
            navigation={navigation}
            bankInfo={bankInfo}
            setEmpty={setEmpty}
            getNewToken={getNewToken}
            getMyInfo={getMyInfo}
          />
        ) : (
          <HasAccount
            navigation={navigation}
            bankInfo={bankInfo}
            setBankInfo={setBankInfo}
            setEmpty={setEmpty}
            getMyInfo={getMyInfo}
          />
        )}
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default ConnectAccount;
