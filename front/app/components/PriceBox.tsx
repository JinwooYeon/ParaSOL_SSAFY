import { View, Text, TouchableOpacity } from "react-native";
import {
  PriceBtn,
  PriceBtnContainer,
  PriceBtnText,
  PriceInput,
  PriceInputContainer,
  PriceInputText,
  PriceInputTextContainer,
} from "../screens/styled";

interface PropsType {
  // 금액
  price: string;
  // 금액 set
  setPrice: (a: any) => void;
}

// Component _ PriceBox
const PriceBox: React.FC<PropsType> = ({ price, setPrice }) => {
  // method
  // 세자리 수마다 콤마 찍기
  const onChangeText = (s: string) => {
    s = s.replace(/[^0-9]/g, "");
    if (s === "") s = "0";
    let temp = s.slice(0, 1);
    if (s.length > 1 && temp === "0") {
      s = s.substring(1);
    }
    let rep = s.replace(/,/g, "");
    setPrice(rep.replace(/\B(?=(\d{3})+(?!\d))/g, ","));
  };
  // 콤마 제거
  const onPress = (n: number) => {
    let rep = parseInt(price.replace(/,/g, "")) + n;
    setPrice(rep.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
  };

  return (
    <View>
      <PriceInputContainer>
        <PriceInputText>금액</PriceInputText>
        <PriceInputTextContainer>
          <PriceInput
            textAlign={"right"}
            keyboardType={"numeric"}
            value={price}
            onChangeText={onChangeText}
          />
          <PriceInputText>원</PriceInputText>
          <TouchableOpacity onPress={() => setPrice("0")}>
            <PriceInputText>×</PriceInputText>
          </TouchableOpacity>
        </PriceInputTextContainer>
      </PriceInputContainer>
      <PriceBtnContainer>
        <PriceBtn onPress={() => onPress(50000)}>
          <PriceBtnText>+5만원</PriceBtnText>
        </PriceBtn>
        <PriceBtn onPress={() => onPress(30000)}>
          <PriceBtnText>+3만원</PriceBtnText>
        </PriceBtn>
        <PriceBtn onPress={() => onPress(10000)}>
          <PriceBtnText>+1만원</PriceBtnText>
        </PriceBtn>
      </PriceBtnContainer>
    </View>
  );
};

export default PriceBox;
