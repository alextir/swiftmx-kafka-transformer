/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.swiftmxtransformer.model;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class MxType extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8012412283535839030L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"MxType\",\"namespace\":\"org.swiftmxtransformer.model\",\"fields\":[{\"name\":\"businessProcess\",\"type\":\"string\"},{\"name\":\"functionality\",\"type\":\"string\"},{\"name\":\"variant\",\"type\":\"string\"},{\"name\":\"version\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<MxType> ENCODER =
      new BinaryMessageEncoder<MxType>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<MxType> DECODER =
      new BinaryMessageDecoder<MxType>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<MxType> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<MxType> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<MxType> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<MxType>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this MxType to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a MxType from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a MxType instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static MxType fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.CharSequence businessProcess;
  @Deprecated public java.lang.CharSequence functionality;
  @Deprecated public java.lang.CharSequence variant;
  @Deprecated public java.lang.CharSequence version;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public MxType() {}

  /**
   * All-args constructor.
   * @param businessProcess The new value for businessProcess
   * @param functionality The new value for functionality
   * @param variant The new value for variant
   * @param version The new value for version
   */
  public MxType(java.lang.CharSequence businessProcess, java.lang.CharSequence functionality, java.lang.CharSequence variant, java.lang.CharSequence version) {
    this.businessProcess = businessProcess;
    this.functionality = functionality;
    this.variant = variant;
    this.version = version;
  }

  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return businessProcess;
    case 1: return functionality;
    case 2: return variant;
    case 3: return version;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: businessProcess = (java.lang.CharSequence)value$; break;
    case 1: functionality = (java.lang.CharSequence)value$; break;
    case 2: variant = (java.lang.CharSequence)value$; break;
    case 3: version = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'businessProcess' field.
   * @return The value of the 'businessProcess' field.
   */
  public java.lang.CharSequence getBusinessProcess() {
    return businessProcess;
  }


  /**
   * Sets the value of the 'businessProcess' field.
   * @param value the value to set.
   */
  public void setBusinessProcess(java.lang.CharSequence value) {
    this.businessProcess = value;
  }

  /**
   * Gets the value of the 'functionality' field.
   * @return The value of the 'functionality' field.
   */
  public java.lang.CharSequence getFunctionality() {
    return functionality;
  }


  /**
   * Sets the value of the 'functionality' field.
   * @param value the value to set.
   */
  public void setFunctionality(java.lang.CharSequence value) {
    this.functionality = value;
  }

  /**
   * Gets the value of the 'variant' field.
   * @return The value of the 'variant' field.
   */
  public java.lang.CharSequence getVariant() {
    return variant;
  }


  /**
   * Sets the value of the 'variant' field.
   * @param value the value to set.
   */
  public void setVariant(java.lang.CharSequence value) {
    this.variant = value;
  }

  /**
   * Gets the value of the 'version' field.
   * @return The value of the 'version' field.
   */
  public java.lang.CharSequence getVersion() {
    return version;
  }


  /**
   * Sets the value of the 'version' field.
   * @param value the value to set.
   */
  public void setVersion(java.lang.CharSequence value) {
    this.version = value;
  }

  /**
   * Creates a new MxType RecordBuilder.
   * @return A new MxType RecordBuilder
   */
  public static org.swiftmxtransformer.model.MxType.Builder newBuilder() {
    return new org.swiftmxtransformer.model.MxType.Builder();
  }

  /**
   * Creates a new MxType RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new MxType RecordBuilder
   */
  public static org.swiftmxtransformer.model.MxType.Builder newBuilder(org.swiftmxtransformer.model.MxType.Builder other) {
    if (other == null) {
      return new org.swiftmxtransformer.model.MxType.Builder();
    } else {
      return new org.swiftmxtransformer.model.MxType.Builder(other);
    }
  }

  /**
   * Creates a new MxType RecordBuilder by copying an existing MxType instance.
   * @param other The existing instance to copy.
   * @return A new MxType RecordBuilder
   */
  public static org.swiftmxtransformer.model.MxType.Builder newBuilder(org.swiftmxtransformer.model.MxType other) {
    if (other == null) {
      return new org.swiftmxtransformer.model.MxType.Builder();
    } else {
      return new org.swiftmxtransformer.model.MxType.Builder(other);
    }
  }

  /**
   * RecordBuilder for MxType instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<MxType>
    implements org.apache.avro.data.RecordBuilder<MxType> {

    private java.lang.CharSequence businessProcess;
    private java.lang.CharSequence functionality;
    private java.lang.CharSequence variant;
    private java.lang.CharSequence version;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.swiftmxtransformer.model.MxType.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.businessProcess)) {
        this.businessProcess = data().deepCopy(fields()[0].schema(), other.businessProcess);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.functionality)) {
        this.functionality = data().deepCopy(fields()[1].schema(), other.functionality);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.variant)) {
        this.variant = data().deepCopy(fields()[2].schema(), other.variant);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.version)) {
        this.version = data().deepCopy(fields()[3].schema(), other.version);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
    }

    /**
     * Creates a Builder by copying an existing MxType instance
     * @param other The existing instance to copy.
     */
    private Builder(org.swiftmxtransformer.model.MxType other) {
      super(SCHEMA$);
      if (isValidValue(fields()[0], other.businessProcess)) {
        this.businessProcess = data().deepCopy(fields()[0].schema(), other.businessProcess);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.functionality)) {
        this.functionality = data().deepCopy(fields()[1].schema(), other.functionality);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.variant)) {
        this.variant = data().deepCopy(fields()[2].schema(), other.variant);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.version)) {
        this.version = data().deepCopy(fields()[3].schema(), other.version);
        fieldSetFlags()[3] = true;
      }
    }

    /**
      * Gets the value of the 'businessProcess' field.
      * @return The value.
      */
    public java.lang.CharSequence getBusinessProcess() {
      return businessProcess;
    }


    /**
      * Sets the value of the 'businessProcess' field.
      * @param value The value of 'businessProcess'.
      * @return This builder.
      */
    public org.swiftmxtransformer.model.MxType.Builder setBusinessProcess(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.businessProcess = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'businessProcess' field has been set.
      * @return True if the 'businessProcess' field has been set, false otherwise.
      */
    public boolean hasBusinessProcess() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'businessProcess' field.
      * @return This builder.
      */
    public org.swiftmxtransformer.model.MxType.Builder clearBusinessProcess() {
      businessProcess = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'functionality' field.
      * @return The value.
      */
    public java.lang.CharSequence getFunctionality() {
      return functionality;
    }


    /**
      * Sets the value of the 'functionality' field.
      * @param value The value of 'functionality'.
      * @return This builder.
      */
    public org.swiftmxtransformer.model.MxType.Builder setFunctionality(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.functionality = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'functionality' field has been set.
      * @return True if the 'functionality' field has been set, false otherwise.
      */
    public boolean hasFunctionality() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'functionality' field.
      * @return This builder.
      */
    public org.swiftmxtransformer.model.MxType.Builder clearFunctionality() {
      functionality = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'variant' field.
      * @return The value.
      */
    public java.lang.CharSequence getVariant() {
      return variant;
    }


    /**
      * Sets the value of the 'variant' field.
      * @param value The value of 'variant'.
      * @return This builder.
      */
    public org.swiftmxtransformer.model.MxType.Builder setVariant(java.lang.CharSequence value) {
      validate(fields()[2], value);
      this.variant = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'variant' field has been set.
      * @return True if the 'variant' field has been set, false otherwise.
      */
    public boolean hasVariant() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'variant' field.
      * @return This builder.
      */
    public org.swiftmxtransformer.model.MxType.Builder clearVariant() {
      variant = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'version' field.
      * @return The value.
      */
    public java.lang.CharSequence getVersion() {
      return version;
    }


    /**
      * Sets the value of the 'version' field.
      * @param value The value of 'version'.
      * @return This builder.
      */
    public org.swiftmxtransformer.model.MxType.Builder setVersion(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.version = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'version' field has been set.
      * @return True if the 'version' field has been set, false otherwise.
      */
    public boolean hasVersion() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'version' field.
      * @return This builder.
      */
    public org.swiftmxtransformer.model.MxType.Builder clearVersion() {
      version = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public MxType build() {
      try {
        MxType record = new MxType();
        record.businessProcess = fieldSetFlags()[0] ? this.businessProcess : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.functionality = fieldSetFlags()[1] ? this.functionality : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.variant = fieldSetFlags()[2] ? this.variant : (java.lang.CharSequence) defaultValue(fields()[2]);
        record.version = fieldSetFlags()[3] ? this.version : (java.lang.CharSequence) defaultValue(fields()[3]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<MxType>
    WRITER$ = (org.apache.avro.io.DatumWriter<MxType>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<MxType>
    READER$ = (org.apache.avro.io.DatumReader<MxType>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.businessProcess);

    out.writeString(this.functionality);

    out.writeString(this.variant);

    out.writeString(this.version);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.businessProcess = in.readString(this.businessProcess instanceof Utf8 ? (Utf8)this.businessProcess : null);

      this.functionality = in.readString(this.functionality instanceof Utf8 ? (Utf8)this.functionality : null);

      this.variant = in.readString(this.variant instanceof Utf8 ? (Utf8)this.variant : null);

      this.version = in.readString(this.version instanceof Utf8 ? (Utf8)this.version : null);

    } else {
      for (int i = 0; i < 4; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.businessProcess = in.readString(this.businessProcess instanceof Utf8 ? (Utf8)this.businessProcess : null);
          break;

        case 1:
          this.functionality = in.readString(this.functionality instanceof Utf8 ? (Utf8)this.functionality : null);
          break;

        case 2:
          this.variant = in.readString(this.variant instanceof Utf8 ? (Utf8)this.variant : null);
          break;

        case 3:
          this.version = in.readString(this.version instanceof Utf8 ? (Utf8)this.version : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










