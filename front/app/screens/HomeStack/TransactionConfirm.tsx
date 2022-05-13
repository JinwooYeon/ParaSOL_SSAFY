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

interface PropsType {
  // 송금할 주소
  info: string;
  // 송금할 금액
  price: string;
  // stack navigation
  navigation: any;
}

// Component _ TransactionConfirm
const TransactionConfirm: React.FC<PropsType> = ({
  navigation: { navigate, goBack },
  info,
  price,
}) => {
  // useState
  // 로딩
  const [loading, setLoading] = useState<boolean>(false);

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
      setTimeout(() => {
        setLoading(false);
        navigate?.("HomeMain");
      }, 2000);
      console.log("success");
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
          <ConfirmBtnTouchableOpacity onPress={biometricsAuth} ok={true}>
            <ConfirmBtnText>확인</ConfirmBtnText>
          </ConfirmBtnTouchableOpacity>
        </ConfirmBtnContainer>
      </ConfirmContainer>
    );
  }
};

export default TransactionConfirm;
