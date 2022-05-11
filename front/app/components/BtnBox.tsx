import { Alert } from "react-native";
import { Btn, BtnContainer, BtnText } from "../screens/styled";

interface PropsType {
  color: string;
  text: string;
  setPrice?: (a: string) => void;
  setLogin?: (a: boolean) => void;
  setter?: any;
  navigation?: any;
  setCharge?: (a: boolean) => void;
  transactionData?: { info: string; price: string };
  payData?: { bankInfo: any; price: string };
}

const BtnBox: React.FC<PropsType> = ({
  color,
  text,
  setPrice,
  setLogin,
  setter,
  navigation,
  setCharge,
  transactionData,
  payData,
}) => {
  let msg = "";

  const onPress = () => {
    switch (text) {
      case "충전하기":
        if (payData?.bankInfo.bankNum !== "" && payData?.price !== "0") {
          console.log("charge");
          setCharge?.(true);
          navigation?.navigate("PayConfirm");
        } else {
          if (payData.bankInfo.bankNum === "") {
            console.log("info empty");
            msg = "계좌를 연결해주세요!";
          } else if (payData.price === "0") {
            console.log("price empty");
            msg = "금액을 채워주세요!";
          }
          Alert.alert("알림", msg, [
            {
              text: "확인",
            },
          ]);
        }
        break;
      case "출금하기":
        if (payData?.bankInfo.bankNum !== "" && payData?.price !== "0") {
          console.log("withdraw");
          setCharge?.(false);
          navigation?.navigate("PayConfirm");
        } else {
          if (payData.bankInfo.bankNum === "") {
            console.log("info empty");
            msg = "계좌를 연결해주세요!";
          } else if (payData.price === "0") {
            console.log("price empty");
            msg = "금액을 채워주세요!";
          }
          Alert.alert("알림", msg, [
            {
              text: "확인",
            },
          ]);
        }
        break;
      case "송금하기":
        console.log("transaction");
        navigation?.navigate("Transaction");
        break;
      case "다음":
        if (transactionData?.info !== "" && transactionData?.price !== "0") {
          console.log("Next");
          navigation?.navigate("TransactionConfirm");
        } else {
          if (transactionData.info === "") {
            console.log("info empty");
            msg = "송금할 주소를 채워주세요!";
          } else if (transactionData.price === "0") {
            console.log("price empty");
            msg = "금액을 채워주세요!";
          }
          Alert.alert("알림", msg, [
            {
              text: "확인",
            },
          ]);
        }
        break;
      case "정보 수정":
        console.log("update info");
        // navigation?.navigate("UpdateProfile");
        setter?.(true);
        break;
      case "수정 완료":
        console.log("complete update");
        setter?.(false);
        break;
      case "비밀번호 수정":
        console.log("update password");
        break;
      case "회원 탈퇴":
        console.log("delete info");
        setter?.();
        // setLogin?.(false);
        break;
      case "초기화":
        console.log("reset");
        setPrice?.("0");
        break;
      case "뒤로":
        navigation?.goBack();
        console.log("back");
        break;
      case "로그인":
        console.log("login");
        setter?.();
        // setLogin?.(true);
        break;
      case "회원가입":
        console.log("register");
        setter?.();
        break;
      case "비밀번호 재설정":
        console.log("new Password");
        setter?.();
        break;
      default:
        console.log("set text props");
    }
  };

  return (
    <BtnContainer>
      <Btn color={color} onPress={onPress}>
        <BtnText white={color === "white" ? true : false}>{text}</BtnText>
      </Btn>
    </BtnContainer>
  );
};

export default BtnBox;
