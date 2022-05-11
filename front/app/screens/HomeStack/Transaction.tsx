import { TouchableOpacity } from "react-native";
import BalanceBox from "../../components/BalanceBox";
import BtnBox from "../../components/BtnBox";
import PriceBox from "../../components/PriceBox";
import {
  ContentContainer,
  ContentFooterContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  TransactionContainer,
  TransactionTextDel,
  TransactionTextInput,
  TransactionTextDelTouchableOpacity,
} from "../styled";

interface PropsType {
  balance: string;
  navigation: any;
  info: string;
  setInfo: (a: string) => void;
  price: string;
  setPrice: (a: string) => void;
}

const Transaction: React.FC<PropsType> = ({
  balance,
  navigation,
  info,
  setInfo,
  price,
  setPrice,
}) => {
  const transactionData = { info, price };

  // method
  const onChangeText = (s: string) => {
    setInfo(s);
  };
  const onPress = () => {
    setInfo("");
  };

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="파라솔 PAY" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          <TransactionContainer>
            <TransactionTextInput
              placeholder="송금할 주소를 입력해주세요."
              value={info}
              onChangeText={onChangeText}
            />
            <TransactionTextDelTouchableOpacity onPress={onPress}>
              <TransactionTextDel>X</TransactionTextDel>
            </TransactionTextDelTouchableOpacity>
          </TransactionContainer>
          <PriceBox price={price} setPrice={setPrice} />
        </ContentContainer>
        <FooterContainer>
          <BtnBox
            color="blue"
            text="다음"
            navigation={navigation}
            transactionData={transactionData}
          />
          <BtnBox color="white" text="뒤로" navigation={navigation} />
          <BtnBox
            color="red"
            text="초기화"
            navigation={navigation}
            setPrice={setPrice}
          />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Transaction;
