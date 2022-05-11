import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { useEffect, useState } from "react";
import { Text, TouchableOpacity, View } from "react-native";
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
  const onPressConfirm = () => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
      navigate?.("PayMain");
    }, 2000);
  };
  const onPressCancel = () => {
    goBack();
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
          <ConfirmBtnTouchableOpacity onPress={onPressConfirm} ok={true}>
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
