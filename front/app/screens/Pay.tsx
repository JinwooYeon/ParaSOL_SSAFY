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
  BtnContainerRow,
} from "./styled";

interface PropsType {
  // 잔액
  balance: string;
  // 계좌 연결 정보
  bankInfo: any;
  // 금액
  price: string;
  // 금액 set
  setPrice: (a: string) => void;
  // 충전 or 출금 set
  setCharge: (a: boolean) => void;
  // stack navigation
  navigation: any;
  // 잔액 set
  setBalance: (a: string) => void;
  // 새로운 인증 토큰 발급
  getNewToken: () => Promise<any>;
}

// Component _ Pay
const Pay: React.FC<PropsType> = ({
  balance,
  bankInfo,
  price,
  setPrice,
  setCharge,
  navigation,
  setBalance,
  getNewToken,
}) => {
  // const
  // 충전 or 출금 데이터
  const payData = {
    bankInfo,
    price,
  };

  return (
    <LayoutContainer>
      <HeaderContainer>
        <BalanceBox
          category="PAY 충전 / 출금"
          num={balance}
          setBalance={setBalance}
          getNewToken={getNewToken}
        />
      </HeaderContainer>
      <ContentFooterContainer>
        <ContentContainer>
          <ConnectedAccountBox bankInfo={bankInfo} navigation={navigation} />
          <PriceBox price={price} setPrice={setPrice} />
        </ContentContainer>
        <FooterContainer>
          <BtnContainerRow>
            <BtnBox
              color="white"
              text="출금하기"
              navigation={navigation}
              setCharge={setCharge}
              payData={payData}
            />
            <BtnBox
              color="blue"
              text="충전하기"
              navigation={navigation}
              setCharge={setCharge}
              payData={payData}
            />
          </BtnContainerRow>
        </FooterContainer>
      </ContentFooterContainer>
    </LayoutContainer>
  );
};

export default Pay;
