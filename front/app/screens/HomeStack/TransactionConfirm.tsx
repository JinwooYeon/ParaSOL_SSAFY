import { TouchableOpacity, Text } from "react-native";
import { ConfirmContainer } from "../styled";

interface PropsType {
  navigation: any;
  info: string;
  price: string;
}

const TransactionConfirm: React.FC<PropsType> = ({
  navigation: { navigate, goBack },
  info,
  price,
}) => {
  return (
    <ConfirmContainer>
      <Text>{info} 으로</Text>
      <Text>{price}원을 송금하시겠습니까?</Text>
      <TouchableOpacity onPress={() => navigate?.("HomeMain")}>
        <Text>확인</Text>
      </TouchableOpacity>
      <TouchableOpacity onPress={() => goBack()}>
        <Text>취소</Text>
      </TouchableOpacity>
    </ConfirmContainer>
  );
};

export default TransactionConfirm;
