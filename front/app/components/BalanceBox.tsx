import { useState } from "react";
import { RefreshControl, ScrollView, View } from "react-native";
import {
  Balance,
  BoxContainer,
  BalanceTextContainer,
  HeaderText,
} from "../screens/styled";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

interface PropsType {
  // 거래내역 or 페이 or 홈
  category: string;
  // 잔액
  num: string;
  // 잔액 set
  setBalance: (a: string) => void;
  // 새로운 인증 토큰 발급
  getNewToken: () => Promise<any>;
}

// Component _ BalanceBox
const BalanceBox: React.FC<PropsType> = ({
  category,
  num,
  setBalance,
  getNewToken,
}) => {
  // const
  // Axios 내 정보 조회 url
  const getMyInfoUrl = "http://k6S101.p.ssafy.io:8080/pay";

  // useState
  // 리프레쉬
  const [refreshing, setRefreshing] = useState(false);

  // Axios
  // 내 정보 조회
  const getMyInfo = async () => {
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "get",
      url: getMyInfoUrl,
      headers: { Authorization: `Bearer ${accessToken}` },
    })
      .then((res) => {
        console.log(res.data);
        setBalance(res.data.balance);
        setTimeout(() => setRefreshing(false), 2000);
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken?.())) {
          getMyInfo();
        } else {
          setTimeout(() => setRefreshing(false), 2000);
        }
      });
  };

  // method
  const onRefresh = () => {
    setRefreshing(true);
    getMyInfo();
  };

  return (
    <ScrollView
      refreshControl={
        <RefreshControl refreshing={refreshing} onRefresh={onRefresh} />
      }
      scrollEnabled={false}
    >
      <View>
        <HeaderText>{category}</HeaderText>
        <BoxContainer>
          <Balance>잔액</Balance>
          <BalanceTextContainer>
            <Balance>{num}</Balance>
            <Balance>원</Balance>
          </BalanceTextContainer>
        </BoxContainer>
      </View>
    </ScrollView>
  );
};

export default BalanceBox;
