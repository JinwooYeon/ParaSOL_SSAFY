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
  const [loading, setLoading] = useState(false);

  // method
  const onPressConfirm = () => {
    setLoading(true);
    setTimeout(() => {
      setLoading(false);
      navigate?.("HomeMain");
    }, 2000);
  };
  const onPressCancel = () => {
    goBack();
  };
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
          <ConfirmBtnTouchableOpacity onPress={onPressConfirm} ok={true}>
            <ConfirmBtnText>확인</ConfirmBtnText>
          </ConfirmBtnTouchableOpacity>
        </ConfirmBtnContainer>
      </ConfirmContainer>
    );
  }
};

export default TransactionConfirm;
