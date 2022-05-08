import { Btn, BtnContainer, BtnText } from "../screens/styled";

interface PropsType {
  color: string;
  text: string;
  setPrice?: (a: string) => void;
  navigation?: any;
}

const BtnBox: React.FC<PropsType> = ({ color, text, setPrice, navigation }) => {
  const onPress = () => {
    switch (text) {
      case "충전하기":
        console.log("charge");
        navigation?.navigate("Charging");
        break;
      case "출금하기":
        console.log("withdraw");
        navigation?.navigate("Withdrawing");
        break;
      case "결제하기":
        console.log("pay");
        break;
      case "정보 수정":
        console.log("update info");
        break;
      case "수정 완료":
        console.log("complete update");
        break;
      case "비밀번호 수정":
        console.log("update password");
        break;
      case "회원 탈퇴":
        console.log("delete info");
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
