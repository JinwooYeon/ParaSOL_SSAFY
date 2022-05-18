import {
  BenefitContainer,
  BenefitText,
  HeaderText,
  LayoutContainer,
} from "./styled";

// Component _ Benefit
const Benefit = () => (
  <LayoutContainer>
    <HeaderText>혜택</HeaderText>
    <BenefitContainer>
      <BenefitText>진행중인 이벤트가 없습니다.</BenefitText>
    </BenefitContainer>
  </LayoutContainer>
);

export default Benefit;
