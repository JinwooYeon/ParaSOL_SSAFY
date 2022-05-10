import { Btn, BtnContainer, BtnText } from "../screens/styled";

interface PropsType {
  color: string;
  text: string;
  setPrice?: (a: string) => void;
  setLogin?: (a: boolean) => void;
  setter?: any;
  navigation?: any;
  setMethod?: (a: boolean) => void;
}

const BtnBox: React.FC<PropsType> = ({
  color,
  text,
  setPrice,
  setLogin,
  setter,
  navigation,
  setMethod,
}) => {
  const onPress = () => {
    switch (text) {
      case "충전하기":
        console.log("charge");
        setMethod?.(true);
        navigation?.navigate("PayConfirm");
        break;
      case "출금하기":
        console.log("withdraw");
        setMethod?.(false);
        navigation?.navigate("PayConfirm");
        break;
      case "송금하기":
        console.log("transaction");
        navigation?.navigate("Transaction");
        break;
      case "다음":
        console.log("Next");
        navigation?.navigate("TransactionConfirm");
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
        navigation?.navigate("login");
        break;
      case "비밀번호 재발급":
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
