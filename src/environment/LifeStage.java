package environment;

public enum LifeStage {
    JUVENILE {
        @Override
        public boolean canReproduce() { return false; }
        @Override
        public LifeStage next() { return ADULT; }
    },
    ADULT {
        @Override
        public boolean canReproduce() { return true; }
        @Override
        public LifeStage next() { return SENESCENT; }
    },
    SENESCENT {
        @Override
        public boolean canReproduce() { return false; }
        @Override
        public LifeStage next() { return SENESCENT; }
    };

    public abstract boolean canReproduce();
    public abstract LifeStage next();
}