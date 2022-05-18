import { useState } from "react";
import Loading from "../Loading";
import {
  ConfirmBtnContainer,
  ConfirmContainer,
  ConfirmTargetContainer,
  ConfirmBtnTouchableOpacity,
  ConfirmBtnText,
  ConfirmTargetText,
} from "../styled";
import * as LocalAuthentication from "expo-local-authentication";
import { Alert } from "react-native";
import AsyncStorage from "@react-native-async-storage/async-storage";
import axios from "axios";

interface PropsType {
  // 송금할 주소
  info: string;
  // 송금할 금액
  price: string;
  // stack navigation
  navigation: any;
  // 새로운 인증 토큰 발급
  getNewToken: () => Promise<any>;
  // 2차 인증 정보 등록 여부
  auth: any;
  // 잔액 set
  setBalance: (a: string) => void;
}

// Component _ TransactionConfirm
const TransactionConfirm: React.FC<PropsType> = ({
  navigation: { navigate, goBack },
  info,
  price,
  getNewToken,
  auth,
  setBalance,
}) => {
  // const
  // Axios url
  const url = "/pay/transaction";

  // useState
  // 로딩
  const [loading, setLoading] = useState<boolean>(false);

  // let
  // 콤마 제거
  let delPrice = parseInt(price.replace(/,/g, ""));

  // Axios
  // 송금
  const transationPost = async () => {
    const accessToken = await AsyncStorage.getItem("accessToken");
    await axios({
      method: "post",
      url: url,
      headers: { Authorization: `Bearer ${accessToken}` },
      data: {
        method: "transaction",
        price: delPrice,
        transactionTo: info,
      },
    })
      .then((res) => {
        console.log(res.data);
        setBalance(res.data.balance);
        Alert.alert("송금 완료!");
        setTimeout(() => {
          setLoading(false);
          navigate?.("HomeMain");
        }, 1500);
      })
      .catch(async (err) => {
        console.log(err);
        if (err.response.status === 401 && (await getNewToken())) {
          transationPost();
        } else {
          setLoading(false);
          navigate?.("HomeMain");
          Alert.alert("송금에 실패하였습니다.");
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
      transationPost();
    }
    return;
  };

  // 송금중~
  if (loading) {
    return <Loading method="송금" />;
  } else {
    return (
      <ConfirmContainer>
        <ConfirmTargetContainer>
          <ConfirmTargetText>{info} 으로</ConfirmTargetText>
          <ConfirmTargetText>{price}원을 송금하시겠습니까?</ConfirmTargetText>
        </ConfirmTargetContainer>
        <ConfirmBtnContainer>
          <ConfirmBtnTouchableOpacity onPress={onPressCancel} ok={false}>
            <ConfirmBtnText>취소</ConfirmBtnText>
          </ConfirmBtnTouchableOpacity>
          <ConfirmBtnTouchableOpacity
            onPress={
              auth.bio
                ? biometricsAuth
                : () => {
                    setLoading(true);
                    transationPost();
                  }
            }
            ok={true}
          >
            <ConfirmBtnText>확인</ConfirmBtnText>
          </ConfirmBtnTouchableOpacity>
        </ConfirmBtnContainer>
      </ConfirmContainer>
    );
  }
};

export default TransactionConfirm;
