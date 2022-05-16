import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useState } from "react";
import Loading from "../screens/Loading";
import Pay from "../screens/Pay";
import {
  ConfirmBtnContainer,
  ConfirmBtnText,
  ConfirmBtnTouchableOpacity,
  ConfirmContainer,
  ConfirmTargetContainer,
  ConfirmTargetText,
} from "../screens/styled";
import * as LocalAuthentication from "expo-local-authentication";
import { Alert } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

const Stack = createNativeStackNavigator();

interface PropsType {
  // 잔액
  balance: string;
  // 계좌 연결 정보
  bankInfo: any;
  // stack navigation
  navigation: any;
  // 잔액 set
  setBalance: (a: string) => void;
  // 새로운 인증 토큰 발급
  getNewToken: () => Promise<any>;
}
interface PayConfirmPropsType {
  // 충전 or 출금
  charge: boolean;
  // 계좌 연결 정보
  bankInfo: any;
  // 금액
  price: string;
  // stack navigation
  navigation: any;
  // 새로운 인증 토큰 발급
  getNewToken: () => Promise<any>;
}

// Component _ PayConfirm
const PayConfirm: React.FC<PayConfirmPropsType> = ({
  navigation: { navigate, goBack },
  charge,
  price,
  bankInfo,
  getNewToken,
}) => {
  // const
  // Axios url
  const chargeUrl = "http://k6s101.p.ssafy.io:8080/pay/charge";
  const withdrawUrl = "http://k6s101.p.ssafy.io:8080/pay/withdraw";

  // let
  // 콤마 제거
  let delPrice = parseInt(price.replace(/,/g, ""));

  // useState
  const [loading, setLoading] = useState(false);

  // Axios
  // 충전
  const chargePost = async () => {
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "post",
      url: chargeUrl,
      headers: { Authorization: `Bearer ${accessToken}` },
      data: { method: "charge", price: delPrice },
    })
      .then((res) => {
        console.log(res);
        Alert.alert("충전 완료!");
        setTimeout(() => {
          setLoading(false);
          navigate?.("PayMain");
        }, 2000);
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken())) {
          chargePost();
        } else {
          Alert.alert("충전에 실패하였습니다.");
          setTimeout(() => {
            setLoading(false);
            navigate?.("PayMain");
          }, 2000);
        }
      });
  };
  // 출금
  const withdrawPost = async () => {
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "post",
      url: withdrawUrl,
      headers: { Authorization: `Bearer ${accessToken}` },
      data: { method: "withdraw", price: delPrice },
    })
      .then((res) => {
        console.log(res);
        Alert.alert("출금 완료!");
        setTimeout(() => {
          setLoading(false);
          navigate?.("PayMain");
        }, 2000);
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken())) {
          withdrawPost();
        } else {
          Alert.alert("출금에 실패하였습니다.");
          setTimeout(() => {
            setLoading(false);
            navigate?.("PayMain");
          }, 2000);
        }
      });
  };

  // method
  const onPressCancel = () => {
    goBack();
  };

  // LocalAuthentication
  const biometricsAuth = async () => {
    const compatible = await LocalAuthentication.hasHardwareAsync();
    if (!compatible)
      Alert.alert(
        "This device is not compatible for biometric authentication (바이오인증 안됨)"
      );
    const enrolled = await LocalAuthentication.isEnrolledAsync();
    if (!enrolled)
      Alert.alert(
        `'This device doesn't have biometric authentication enabled (바이오인증 없음)`
      );
    const result = await LocalAuthentication.authenticateAsync();
    if (!result.success)
      Alert.alert(`${result.error} - Authentication unsuccessful (인증실패)`);
    if (result.success) {
      setLoading(true);
      if (charge) {
        chargePost();
      } else {
        withdrawPost();
      }
    }
    return;
  };

  if (loading) {
    // 충전중~ or 출금중~
    return <Loading method={charge ? "충전" : "출금"} />;
  } else {
    return (
      <ConfirmContainer>
        <ConfirmTargetContainer>
          <ConfirmTargetText>{bankInfo.bankName}</ConfirmTargetText>
          <ConfirmTargetText>{bankInfo.bankNum} 으로</ConfirmTargetText>
          <ConfirmTargetText>
            {price}원을 {charge ? "충전" : "출금"}하시겠습니까?
          </ConfirmTargetText>
        </ConfirmTargetContainer>
        <ConfirmBtnContainer>
          <ConfirmBtnTouchableOpacity onPress={onPressCancel} ok={false}>
            <ConfirmBtnText>취소</ConfirmBtnText>
          </ConfirmBtnTouchableOpacity>
          <ConfirmBtnTouchableOpacity onPress={biometricsAuth} ok={true}>
            <ConfirmBtnText>확인</ConfirmBtnText>
          </ConfirmBtnTouchableOpacity>
        </ConfirmBtnContainer>
      </ConfirmContainer>
    );
  }
};

// Component _ PayStack
const PayStack: React.FC<PropsType> = ({
  balance,
  bankInfo,
  navigation,
  setBalance,
  getNewToken,
}) => {
  // useState
  // 충전 or 출금
  const [charge, setCharge] = useState<boolean>(false);
  // 금액
  const [price, setPrice] = useState<string>("0");

  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_bottom",
      }}
    >
      {/* 페이 첫 스크린 */}
      <Stack.Screen name="PayMain">
        {(props) => (
          <Pay
            {...props}
            balance={balance}
            bankInfo={bankInfo}
            price={price}
            setPrice={setPrice}
            navigation={navigation}
            setCharge={setCharge}
            setBalance={setBalance}
            getNewToken={getNewToken}
          />
        )}
      </Stack.Screen>
      {/* 충전 or 출금 확인 */}
      <Stack.Screen name="PayConfirm">
        {(props) => (
          <PayConfirm
            {...props}
            charge={charge}
            bankInfo={bankInfo}
            price={price}
            getNewToken={getNewToken}
          />
        )}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default PayStack;
