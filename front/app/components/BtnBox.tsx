import { Btn, BtnContainer, BtnText } from "../screens/styled";

interface PropsType {
  color: string;
  text: string;
  setPrice?: (a: string) => void;
}

const BtnBox: React.FC<PropsType> = ({ color, text, setPrice }) => {
  const onPress = () => {
    switch (text) {
      case "충전하기":
        console.log("charge");
        break;
      case "출금하기":
        console.log("withdraw");
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
        {
          console.log("reset");
          setPrice?.("0");
        }
        break;
      case "뒤로":
        console.log("back");
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
