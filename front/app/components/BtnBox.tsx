import { Alert } from "react-native";
import { Btn, BtnContainer, BtnText } from "../screens/styled";

interface PropsType {
  // 버튼 색상
  color: string;
  // 버튼 텍스트
  text: string;
  // 송금 데이터
  transactionData?: { info: string; price: string };
  // 충전 or 출금 데이터
  payData?: { bankInfo: any; price: string };
  // set 함수
  setter?: any;
  // 충전 or 출금 set
  setCharge?: (a: boolean) => void;
  // stack navigation
  navigation?: any;
}

// Component _ BtnBox
const BtnBox: React.FC<PropsType> = ({
  color,
  text,
  setter,
  navigation,
  setCharge,
  transactionData,
  payData,
}) => {
  // let
  // 알림 메시지
  let msg = "";

  const onPress = () => {
    switch (text) {
      // QR 스캔
      case "QR 스캔":
        navigation?.navigate("Scanner");
        break;
      // 충전하기
      case "충전하기":
        if (payData?.bankInfo.bankNum !== "" && payData?.price !== "0") {
          setCharge?.(true);
          navigation?.navigate("PayConfirm");
        } else {
          if (payData.bankInfo.bankNum === "") {
            msg = "계좌를 연결해주세요!";
          } else if (payData.price === "0") {
            msg = "금액을 채워주세요!";
          }
          Alert.alert("알림", msg, [
            {
              text: "확인",
            },
          ]);
        }
        break;
      // 출금하기
      case "출금하기":
        if (payData?.bankInfo.bankNum !== "" && payData?.price !== "0") {
          setCharge?.(false);
          navigation?.navigate("PayConfirm");
        } else {
          if (payData.bankInfo.bankNum === "") {
            msg = "계좌를 연결해주세요!";
          } else if (payData.price === "0") {
            msg = "금액을 채워주세요!";
          }
          Alert.alert("알림", msg, [
            {
              text: "확인",
            },
          ]);
        }
        break;
      // 송금하기
      case "송금하기":
        navigation?.navigate("Transaction");
        break;
      // 다음
      case "다음":
        if (transactionData?.info !== "" && transactionData?.price !== "0") {
          navigation?.navigate("TransactionConfirm");
        } else {
          if (transactionData.info === "") {
            msg = "송금할 주소를 채워주세요!";
          } else if (transactionData.price === "0") {
            msg = "금액을 채워주세요!";
          }
          Alert.alert("알림", msg, [
            {
              text: "확인",
            },
          ]);
        }
        break;
      // 수정 완료
      case "수정 완료":
        setter?.();
        break;
      // 비밀번호 수정
      case "비밀번호 수정":
        setter?.(true);
        break;
      // 회원 탈퇴
      case "회원 탈퇴":
        setter?.();
        break;
      // 뒤로
      case "뒤로":
        navigation?.goBack();
        break;
      // 취소
      case "취소":
        setter?.(false);
        break;
      // 로그인
      case "로그인":
        setter?.();
        break;
      // 아이디 중복 체크
      case "아이디 중복 체크":
        setter?.();
        break;
      // 회원가입
      case "회원가입":
        setter?.();
        break;
      // 비밀번호 재설정
      case "비밀번호 재설정":
        setter?.();
        break;
      default:
        Alert.alert("set text props");
        break;
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
