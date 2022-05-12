import {
  BenefitContainer,
  BenefitText,
  HeaderText,
  LayoutContainer,
} from "./styled";

const Benefit = () => (
  <LayoutContainer>
    <HeaderText>혜택</HeaderText>
    <BenefitContainer>
      <BenefitText>진행중인 이벤트가 없습니다.</BenefitText>
    </BenefitContainer>
  </LayoutContainer>
);

export default Benefit;
