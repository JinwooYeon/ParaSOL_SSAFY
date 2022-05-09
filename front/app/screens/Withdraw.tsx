import { useState } from "react";
import BalanceBox from "../components/BalanceBox";
import BtnBox from "../components/BtnBox";
import ConnectedAccountBox from "../components/ConnectedAccountBox";
import PriceBox from "../components/PriceBox";
import {
  ContentContainer,
  FooterContainer,
  HeaderContainer,
  LayoutContainer,
  ContentFooterContainer,
} from "./styled";

interface PropsType {
  balance: string;
  navigation: any;
}

const Withdraw: React.FC<PropsType> = ({
  balance,
  navigation: { navigate },
}) => {
  const [price, setPrice] = useState("0");
  const [bankInfo, setBankInfo] = useState({
    bankImg: "",
    bankName: "",
    bankNum: "",
  });

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox category="PAY 출금" num={balance} />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          <ConnectedAccountBox bankInfo={bankInfo} navigate={navigate} />
          <PriceBox price={price} setPrice={setPrice} />
        </ContentContainer>
        <FooterContainer>
          <BtnBox color="blue" text="출금하기" navigate={navigate} />
          <BtnBox color="red" text="초기화" setPrice={setPrice} />
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Withdraw;
