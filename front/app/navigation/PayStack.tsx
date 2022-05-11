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

const Stack = createNativeStackNavigator();

interface PropsType {
  balance: string;
  bankInfo: any;
  navigation: any;
}
interface PayConfirmPropsType {
  charge: boolean;
  bankInfo: any;
  price: string;
  navigation: any;
}

const PayConfirm: React.FC<PayConfirmPropsType> = ({
  navigation: { navigate, goBack },
  charge,
  price,
  bankInfo,
}) => {
  // useState
  const [loading, setLoading] = useState(false);

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
        navigate?.("PayMain");
      }, 2000);
      console.log("success");
    }
    return;
  };

  if (loading) {
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

const PayStack: React.FC<PropsType> = ({ balance, bankInfo, navigation }) => {
  const [charge, setCharge] = useState(false);
  const [price, setPrice] = useState("0");

  return (
    <Stack.Navigator
      screenOptions={{
        headerShown: false,
        contentStyle: { backgroundColor: "white" },
        animation: "slide_from_bottom",
      }}
    >
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
          />
        )}
      </Stack.Screen>
      <Stack.Screen name="PayConfirm">
        {(props) => (
          <PayConfirm
            {...props}
            charge={charge}
            bankInfo={bankInfo}
            price={price}
          />
        )}
      </Stack.Screen>
    </Stack.Navigator>
  );
};

export default PayStack;
